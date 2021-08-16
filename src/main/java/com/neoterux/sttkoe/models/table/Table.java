package com.neoterux.sttkoe.models.table;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.game.Symbol;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

/**
 * This class contains the game table and its utility
 */
public class Table implements Comparable<Table> {
    /**
     * The utility of the current table
     */
    private int utility;

    /**
     * The grid of the tik tak toe game
     */
    private GridPane gameGrid;

    /**
     * Creates a new Table by the given gameGrid.
     *
     * @param gameGrid the grid of the tik tak toe game
     */
    public Table(GridPane gameGrid){
        this.gameGrid = gameGrid;
        this.utility = 0;
    }

    /**
     * Creates a new empty table
     */
    public Table(){
        this(null);
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
     * Compares the utility of tables.
     *
     * @param o the table to compare
     * @return 0 if utility is equals, < 0 if the utility is lower and > 0 if utility is higher.
     */
    @Override
    public int compareTo (@NotNull Table o) {
        return this.getUtility() - o.getUtility();
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

    public void setGameGrid(GridPane gameGrid) {
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
        GridButton gb = new GridButton();
        gb.setSymbol(value);
        gameGrid.getChildren().set(position, gb);
    }

    /**
     * Printing the table
     */
    public void printTable(){
        for (int i = 1; i < 10; i++) {
            GridButton gb = (GridButton) gameGrid.getChildren().get(i);
            System.out.println(gb.currentSymbol());
        }
    }
}
