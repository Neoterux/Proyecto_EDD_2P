package com.neoterux.sttkoe.game;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GameValidator {
    private Tree<Table> tree;
    private Symbol computerSymbol;
    private Symbol userSymbol;

    public GameValidator(Tree tree){
        this.tree = tree;
    }
    public GameValidator(){}

    //Create a table copy of tree
    /*private Table createTableCopy(Tree<Table> tree){
        String[][] currentMatrix = tree.getRoot().getContent().getTable();
        Table<String> t = new Table<>();
        String matrix[][] = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = currentMatrix[i][j];
            }
        }
        t.setTable(matrix);
        return t;
    }*/

    private PriorityQueue<Tree<Table>> generateChildren(Symbol symbol, Comparator cmp, Tree<Table> tree){
        int count = 0;
        PriorityQueue<Tree<Table>> pq = new PriorityQueue<>(cmp);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
                GridButton btn = (GridButton) tree.getRoot().getContent().getTable().getChildren().get(count);
                Table table = new Table(tree.getRoot().getContent().getTable());
                if (btn.currentSymbol() == null) {
                    table.insertValue(i, j, symbol);
                    pq.add(new Tree<>(new TreeNode<>(table)));
                }
            }
        }
        return pq;
    }

    //Reordering the children with utility
    private void fixingChildren(PriorityQueue<Tree<Table>> movements, Tree<Table> movement, Comparator cmp){
        PriorityQueue<Tree<Table>> finalMovements = new PriorityQueue<>(cmp);
        finalMovements.addAll(movements);

        movement.getRoot().setChildren(finalMovements);
    }

    public void setComputerSymbol(Symbol computerSymbol) {
        this.computerSymbol = computerSymbol;
    }

    public void setUserSymbol(Symbol userSymbol) {
        this.userSymbol = userSymbol;
    }

    public Symbol getComputerSymbol() { return computerSymbol; }

    public Symbol getUserSymbol() { return userSymbol; }

    public void setTree(Tree<Table> tree) {
        this.tree = tree;
    }

    /**
     * Creates a tree to initialize with actual status
     */
    public void initializeTree(){
        PriorityQueue<Tree<Table>> machineMovements = generateChildren(computerSymbol, (Comparator<Tree<Table>>) (e1, e2) -> {
            return e2.getRoot().getContent().getUtility() - e1.getRoot().getContent().getUtility();
        }, this.tree);

        for (Tree<Table> machM: machineMovements) {
            PriorityQueue<Tree<Table>> userMovements = generateChildren(userSymbol, (Comparator<Tree<Table>>) (e1, e2) -> {
                return e1.getRoot().getContent().getUtility() - e2.getRoot().getContent().getUtility();
            }, machM);
            for (Tree<Table> userM: userMovements) {
                userM.getRoot().getContent().generateUtility(computerSymbol, userSymbol);
            }

            fixingChildren(userMovements, machM, (Comparator<Tree<Table>>) (e1, e2) -> {
                return e1.getRoot().getContent().getUtility() - e2.getRoot().getContent().getUtility();
            });

            Tree<Table> bestOption = machM.getChildrenByUtility();
            machM.getRoot().getContent().setUtility(bestOption.getRoot().getContent().getUtility());
        }
        fixingChildren(machineMovements, this.tree, (Comparator<Tree<Table>>) (e1, e2) -> {
            return e2.getRoot().getContent().getUtility() - e1.getRoot().getContent().getUtility();
        });
    }

    /**
     * Getting the best movement to the computer
     * @return A tree with the best movement
     */
    public Tree<Table> getBestOption(){
        return tree.getChildrenByUtility();
    }
}
