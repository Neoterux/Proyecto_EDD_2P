package com.neoterux.sttkoe.game;

import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;

import java.util.ArrayList;

public class GameValidator {
    private Tree<Table<String>> tree;
    private String computerSymbol;

    public GameValidator(Tree<Table<String>> tree){
        this.tree = tree;
    }

    //Create a table copy of tree
    private Table<String> createTableCopy(){
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
    private ArrayList<Table<String>> generateChildren(){
        ArrayList<Table<String>> ar = new ArrayList<Table<String>>();
        String[][] matrix = this.tree.getRoot().getContent().getTable();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Table<String> table = createTableCopy();
                String currentValue = matrix[i][j];

                if(currentValue == "-"){
                    table.insertValue(i, j, "X");
                    ar.add(table);
                }
            }
        }
        return ar;
    }

    /**
     * Creates a tree to initialize the game
     */
    public void createTree(){
        ArrayList<Table<String>> a = generateChildren();
        for (int i = 0; i < a.size(); i++){
            a.get(i).printTable();
            System.out.println("");
        }
    }
}
