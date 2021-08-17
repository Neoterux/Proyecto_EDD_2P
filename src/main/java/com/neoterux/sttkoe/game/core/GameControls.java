package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.models.players.Player;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * A Composite class for the elements inside the Game view Scene.
 * Contains wrapper methods to manage the game info.
 */
@Slf4j
@Getter
public final class GameControls {
    
    /**
     * The table of the game
     */
    @Getter
    private final GridPane table;
    
    /**
     * The grid button matrix, could be replaced by {@code table.getChildren().get(3 * row + col)}
     */
    @Setter private GridButton[][] matrix;
    
    /**
     * The label that contains the name of the current player.
     */
    private final Label turnLabel;
    
    /**
     * The quantity of buttons that have already a symbol.
     */
    private int playedButtonCount;
    
    public GameControls (GridPane table, Label playerLabel) {
        this.table = table;
        this.turnLabel = playerLabel;
        for(Node button : table.getChildren()){
            GridButton tmp = (GridButton) button;
            if (tmp.isBlank())
                playedButtonCount++;
        }
    }
    
    /**
     * Retrieves the button from the GridPane table. Must be lower than accessing into the matrix.
     *
     * @param row the row of the button
     * @param col the col of the button
     * @return the desired Grid Button
     */
    private GridButton getButtonAt(int row, int col){
        // TODO : Check if is lower than the matrix accessing method
        try{
            return (GridButton) this.table.getChildren().get(3 * row + col);
        } catch (NullPointerException npe){
            log.error("This cannot happen, is critical: ", npe);
        } catch (ClassCastException cce) {
            log.error("This GridPane is invalid, doesn't contains the specified object ", cce);
        }
        return null;
    }
    
    /**
     * Set the current player into the turn label.
     *
     * @param player the player that would be written.
     */
    public void setCurrentPlayer (Player player) {
        if (this.turnLabel != null){
            turnLabel.setText(String.format("Turno de: %s",player.getName()));
        }
    }
    
    
    /**
     * Change the button symbol by the inserted player, only if the button doesn't is marked.
     *
     * @param player the player that selected the button
     * @param row the row of the button.
     * @param col the col of the button.
     * @return true if the button can be selected.
     */
    public boolean selectButtonBy (@NotNull Player player, int row, int col) {
        GridButton desired = getButtonAt(row, col);
        if (desired == null || !desired.isBlank())
            return false;
        desired.setAsociatedPlayer(player);
        desired.setSymbol(player.getPlayerSymbol());
        return true;
    }
    
    /**
     * Realizes a check that if all the buttons in the grid have a symbol.
     *
     * @return true if the table is full.
     */
    public boolean tableIsFull(){
        if (table == null)
            return true;
        return playedButtonCount == table.getChildren().size();
    }
    
    /**
     * Closes the window of the application
     */
    public void closeWindow() {
        Stage gameStage = (Stage) this.table.getScene().getWindow();
        gameStage.close();
    }
    
    
}
