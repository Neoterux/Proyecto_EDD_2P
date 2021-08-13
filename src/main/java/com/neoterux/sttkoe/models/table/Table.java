package com.neoterux.sttkoe.models.table;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.game.Symbol;
import javafx.scene.layout.GridPane;

/**
 * This class contains the game table and its utility
 */
public class Table {
    private int utility;
    private GridButton gameGrid;

    public Table(GridPane gameGrid){
        this.gameGrid = gameGrid;
        this.utility = 0;
    }
    public Table(){
        this.utility = 0;
    }

    private Symbol getSymbolGridButton(int i){
        GridButton gb = (GridButton) gameGrid.getChildren().get(i);
        return gb.currentSymbol();
    }

    private boolean rowsAndColumns(Symbol opponentSymbol, int i, int m){
        return getSymbolGridButton(i) != opponentSymbol && getSymbolGridButton(i+m) != opponentSymbol && getSymbolGridButton(i+m*2) != opponentSymbol;
    }

    private int utilityByRows(Symbol opponentSymbol){
        int count = 0;
        for (int i = 1; i <= 7; i += 3) {
            if (rowsAndColumns(opponentSymbol, i, 1))
                count++;
        }
        return count;
    }

    private int utilityByColumns(Symbol opponentSymbol){
        int count = 0;
        for (int i = 1; i <= 3; i++) {
            if (rowsAndColumns(opponentSymbol, i, 3))
                count++;
        }
        return count;
    }

    private int utilityByDiagonals(Symbol opponentSymbol){
        if(getSymbolGridButton(5) == opponentSymbol) return 0;
        int count = 0;
        if(getSymbolGridButton(1) != opponentSymbol && getSymbolGridButton(9) != opponentSymbol) count++;
        if(getSymbolGridButton(3) != opponentSymbol && getSymbolGridButton(7) != opponentSymbol) count++;
        return count;
    }

    /**
     * Getting the table utility
     * @return Table utility
     */
    public int getUtility() {
        return utility;
    }

    public GridPane getTable() {
        return gameGrid;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void setGameGrid(GridButton gameGrid) {
        this.gameGrid = gameGrid;
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
     * @param position Index i in the GridPane
     * @param value The value to be inserted
     */
    public void insertValue(int position, Symbol value){

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
