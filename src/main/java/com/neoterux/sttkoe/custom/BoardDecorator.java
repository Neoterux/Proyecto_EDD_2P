package com.neoterux.sttkoe.custom;

import com.neoterux.sttkoe.game.Difficulty;
import javafx.scene.layout.GridPane;

/**
 * <h1>Board Decorator Class</h1>
 * This class
 */
public class BoardDecorator {

    /**
     * The GridPane that would hold the Buttons for the tik tak toe.
     */
    private final GridPane root;

    /**
     * The game difficulty that would have the evaluator functions.
     */
    private final Difficulty gameDifficulty;


    /**
     * Creates a new BoardDecorator object where the game would be done.
     *
     * @param basePane the pane that would hold the logic.
     */
    public BoardDecorator(GridPane basePane, Difficulty difficulty) {

        this.root = basePane;
        this.gameDifficulty = difficulty;
    }


}
