package com.neoterux.sttkoe.game;

import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GameValidator {
    private final Tree<Table<String>> tree;
    private String computerSymbol;
    private String userSymbol;

    public GameValidator(Tree<Table<String>> tree){
        this.tree = tree;
    }

    //Create a table copy of tree
    private Table<String> createTableCopy(Tree<Table<String>> tree){
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
    }

    //Generates all possible movements
    private PriorityQueue<Tree<Table<String>>> generateChildren(String symbol, Comparator cmp, Tree<Table<String>> tree){

        PriorityQueue<Tree<Table<String>>> pq = new PriorityQueue<>(cmp);

        String[][] matrix = tree.getRoot().getContent().getTable();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Table<String> table = createTableCopy(tree);
                String currentValue = matrix[i][j];

                if(currentValue == "-"){
                    table.insertValue(i, j, symbol);
                    pq.add(new Tree<>(new TreeNode<>(table)));
                }
            }
        }
        return pq;
    }

    //Reordering the children with utility
    private void fixingChildren(PriorityQueue<Tree<Table<String>>> movements, Tree<Table<String>> movement, Comparator cmp){
        PriorityQueue<Tree<Table<String>>> finalMovements = new PriorityQueue<>(cmp);
        finalMovements.addAll(movements);

        movement.getRoot().setChildren(finalMovements);
    }

    public void setComputerSymbol(String computerSymbol) {
        this.computerSymbol = computerSymbol;
    }

    public void setUserSymbol(String userSymbol) {
        this.userSymbol = userSymbol;
    }

    /**
     * Creates a tree to initialize with actual status
     */
    public void initializeTree(){
        //Machine movements
        PriorityQueue<Tree<Table<String>>> machineMovements = generateChildren(computerSymbol, (Comparator<Tree<Table<String>>>) (e1, e2) -> {
            return e2.getRoot().getContent().getUtility() - e1.getRoot().getContent().getUtility();
        }, this.tree);

        for (Tree<Table<String>> machM: machineMovements) {
            //User movements
            PriorityQueue<Tree<Table<String>>> userMovements = generateChildren(userSymbol, (Comparator<Tree<Table<String>>>) (e1, e2) -> {
                return e1.getRoot().getContent().getUtility() - e2.getRoot().getContent().getUtility();
            }, machM);
            for (Tree<Table<String>> userM: userMovements) {
                userM.getRoot().getContent().generateUtility("X", "0");
            }

            fixingChildren(userMovements, machM, (Comparator<Tree<Table<String>>>) (e1, e2) -> {
                return e1.getRoot().getContent().getUtility() - e2.getRoot().getContent().getUtility();
            });

            Tree<Table<String>> bestOption = machM.getChildrenByUtility();
            machM.getRoot().getContent().setUtility(bestOption.getRoot().getContent().getUtility());
        }
        fixingChildren(machineMovements, this.tree, (Comparator<Tree<Table<String>>>) (e1, e2) -> {
            return e2.getRoot().getContent().getUtility() - e1.getRoot().getContent().getUtility();
        });
    }

    /**
     * Getting the best movement to the computer
     * @return A tree with the best movement
     */
    public Tree<Table<String>> getBestOption(){
        return tree.getChildrenByUtility();
    }
}
