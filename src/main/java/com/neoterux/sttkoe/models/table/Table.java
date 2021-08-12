package com.neoterux.sttkoe.models.table;

import com.neoterux.sttkoe.game.Symbol;
import javafx.scene.layout.GridPane;

public class Table {
    private int utility;
    private GridPane gameGrid;

    public Table(GridPane gameGrid){
        this.gameGrid = gameGrid;
        this.utility = 0;
    }
    public Table(){
        this.utility = 0;
    }

    //Getting the number of rows available
    private int utilityByRows(Symbol opponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (gameGrid.getChildren().get(i).get != opponentSymbol && table[i][1] != opponentSymbol && table[i][2] != opponentSymbol)
                count++;
        }
        return count;
    }

    //Getting the number of columns available
    private int utilityByColumns(T opponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (table[0][i] != opponentSymbol && table[1][i] != opponentSymbol && table[2][i] != opponentSymbol)
                count++;
        }
        return count;
    }

    //Getting the number of diagonals available
    private int utilityByDiagonals(T opponentSymbol){
        if(table[1][1] == opponentSymbol) return 0;
        int count = 0;
        if(table[0][0] != opponentSymbol && table[2][2] != opponentSymbol) count++;
        if(table[2][0] != opponentSymbol && table[0][2] != opponentSymbol) count++;
        return count;
    }

    /**
     * Getting the table utility
     * @return Table utility
     */
    public int getUtility() {
        return utility;
    }

    public T[][] getTable() {
        return table;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void setTable(T[][] table) {
        this.table = table;
    }

    /**
     * Generating the table utility
     * @param computerSymbol The symbol the computer uses
     * @param humanSymbol The symbol the user uses
     */
    public void generateUtility(Symbol computerSymbol, Symbol humanSymbol){
        int computer = utilityByRows(humanSymbol) + utilityByColumns(humanSymbol) + utilityByDiagonals(humanSymbol);
        int human = utilityByRows(computerSymbol) + utilityByColumns(computerSymbol) + utilityByDiagonals(computerSymbol);
        setUtility(computer-human);
    }

    /**
     * Inserts a value into the position
     * @param positionI Index i in matrix[i][j]
     * @param positionJ Index j in matrix[i][j]
     * @param value The value to be inserted
     */
    public void insertValue(int positionI, int positionJ, T value){
        table[positionI][positionJ] = value;
    }

    /**
     * Printing the table
     */
    public void printTable(){
        for (T[] row: table) {
            for (T colum: row) {
                System.out.print(colum);
            }
            System.out.println("");
        }
    }
}
