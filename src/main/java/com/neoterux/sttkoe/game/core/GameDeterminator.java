package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.models.players.Player;
import javafx.scene.layout.GridPane;

/**
 * A class that determine a wining position
 */
public abstract class GameDeterminator {
    
    private GameDeterminator() { }
    
    public static GameDeterminator of(GridPane table){
        return new GridDeterminator(table);
    }
    
    public static GameDeterminator of(GridButton[][] matrix){
        return new MatrixDeterminator(matrix);
    }
    
    
    private static class GridDeterminator extends GameDeterminator{
        private GridPane table;
        public GridDeterminator(GridPane pane){
            this.table = pane;
        }
    
        @Override
        public GridButton getAt (int row, int col) {
            return (GridButton) table.getChildren().get(3 * row + col);
        }
    }
    
    private static class MatrixDeterminator extends GameDeterminator {
        private GridButton[][] matrix;
        
        public MatrixDeterminator(GridButton[][] matrix){
            this.matrix = matrix;
        }
    
        @Override
        public GridButton getAt (int row, int col) {
            return matrix[row][col];
        }
    }
    
    
    public abstract GridButton getAt(int row, int col);
    
    /**
     * Validates if the table has a valid combination for determine wining.
     *
     * @param target the target player to validate.
     * @return true if the player has a wining combination, false if not;
     */
    public boolean validateFor (Player target) {
        boolean hasHorizontal = false, hasVertical = false;
        for (int i = 0; i < 3; i++) {
            hasHorizontal = validateRow(target, i);
            if (hasHorizontal) break;
        }
        if(!hasHorizontal)
            for (int i = 0; i < 3; i++) {
                hasVertical = validateVertical(target, i);
                if (hasVertical) break;
            }
            
        return hasHorizontal || hasVertical ||
                validateDiagonal(target,false) ||
                validateDiagonal(target,true);
    }
    
    
    private boolean validateVertical(Player target, int col){
        for (int i = 0; i < 3; i++) {
            if (!hasEqualSymbol(getAt(i,col),target))
                return false;
        }
        
        return true;
    }
    private boolean validateRow(Player target, int row){
        for (int i = 0; i < 3; i++) {
            if (getAt(row, i).currentSymbol() != target.getPlayerSymbol())
                return false;
        }
        return true;
    }
    
    private boolean hasEqualSymbol(GridButton button, Player player){
        return button.currentSymbol() == player.getPlayerSymbol();
    }
    
    private boolean validateDiagonal(Player target, boolean inverted){
        for (int i = 0; i < 3; i++) {
            int pos = (inverted)? i : 2 - i;
            if (getAt(i,pos).currentSymbol() != target.getPlayerSymbol())
                return false;
        }
        return true;
    }
    
    
}
