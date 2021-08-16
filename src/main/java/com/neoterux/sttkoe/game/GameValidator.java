package com.neoterux.sttkoe.game;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeComparator;
import com.neoterux.sttkoe.models.tree.TreeNode;
import com.neoterux.sttkoe.utils.TableUtils;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.PriorityQueue;

@Getter
@Setter
public class GameValidator {
    private Tree<Table> tree;
    
    /**
     * The symbol that the cpu would use
     */
    private Symbol computerSymbol;
    
    /**
     * The symbol of the player
     */
    private Symbol userSymbol;
    
    /**
     * A comparator that have the natural order of a table
     */
    private static final TreeComparator<Table> naturalComparator = Table::compareTo;
    
    /**
     * A comparator that is the inverse of the {@link #naturalComparator}.
     */
    private static final TreeComparator<Table> descComparator = (t1, t2) -> t2.compareTo(t1);

    public GameValidator (Tree<Table> tree){
        this.tree = tree;
    }
    public GameValidator(){}

    private GridPane fillGridPaneWithGridButtons(GridPane gp, GridButton[][] gb){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gp.add(gb[i][j], j, i);
            }
        }
        return gp;
    }

    private GridButton[][] getGridButtonOfGridPane(GridPane gp){
        GridButton[][] gb = new GridButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gb[i][j]  = (GridButton) gp.getChildren().get(3*i + j);
            }
        }
        return gb;
    }

    private PriorityQueue<Tree<Table>> generateChildren(Symbol symbol, Comparator<Tree<Table>> cmp, Tree<Table> tree){
        PriorityQueue<Tree<Table>> pq = new PriorityQueue<>(cmp);
        GridButton[][] newGB = getGridButtonOfGridPane(tree.getRoot().getContent().getTable());
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridButton[][] gb = TableUtils.copyTable(newGB);
                Table table = new Table(fillGridPaneWithGridButtons(new GridPane(), gb));
                if (gb[i][j].currentSymbol() == null) {
                    table.insertValue(count, symbol);
                    pq.add(new Tree<>(new TreeNode<>(table)));
                }
                count++;
            }
        }
        return pq;
    }

    /**
     * Reorder the children by the given comparator, and append to the movement tree.
     *
     * @param movements the movements to order.
     * @param movement the tree where the movements would be appended.
     * @param cmp the comparator to order the movements
     */
    private void fixingChildren(PriorityQueue<Tree<Table>> movements, Tree<Table> movement, Comparator<Tree<Table>> cmp){
        PriorityQueue<Tree<Table>> finalMovements = new PriorityQueue<>(cmp);
        finalMovements.addAll(movements);

        movement.getRoot().setChildren(finalMovements);
    }

    /**
     * Creates a tree to initialize with actual status
     */
    public void initializeTree(){
        PriorityQueue<Tree<Table>> machineMovements = generateChildren(computerSymbol, descComparator, this.tree);
        for (Tree<Table> machM: machineMovements) {
            PriorityQueue<Tree<Table>> userMovements = generateChildren(userSymbol, naturalComparator, machM);
            for (Tree<Table> userM: userMovements) {
                userM.getRoot().getContent().generateUtility(computerSymbol, userSymbol);
            }
            fixingChildren(userMovements, machM, naturalComparator);

            Tree<Table> bestOption = machM.getChildrenByUtility();
            machM.getRoot().getContent().setUtility(bestOption.getRoot().getContent().getUtility());
        }
        fixingChildren(machineMovements, this.tree, descComparator);
    }

    /**
     * Getting the best movement to the computer
     * @return A tree with the best movement
     */
    public Tree<Table> getBestOption(){
        return tree.getChildrenByUtility();
    }
}
