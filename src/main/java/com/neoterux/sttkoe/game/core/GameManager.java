package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.game.Symbol;
import javafx.scene.layout.GridPane;

import java.util.Random;

/**
 * This class has the responsibility to handler the game turn, and other aspects.
 */
public class GameManager {
    
    private final GameMode gameMode;
    
    private GridButton ttbuttons[][];
    
    public GameManager(GameMode mode){
        this.gameMode = mode;
    }
    
    public void fillGrid(GridPane holder){
        ttbuttons = new GridButton[3][3];
        fillMatrix((button, row, col)->{
            holder.add(button,col,row);
        });
    }
    
    private void fillMatrix(FillAction<GridButton> action){
        Random rd = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridButton button = new GridButton();
                ttbuttons[i][j] = button;
                button.fillAllGrid();
                if (rd.nextBoolean())
                    button.setSymbol(Symbol.X);
                int finalI = i;
                int finalJ = j;
                ttbuttons[i][j].setOnAction((eh)->{
                    System.out.println("Button clicked on row:" + finalI + ", col:" + finalJ);
                });
                if (action != null)
                    action.doAction(button, i, j);
                
            }
        }
    }
    
    
    private interface FillAction<T>{
        void doAction(T content, int row, int col);
    }
}
