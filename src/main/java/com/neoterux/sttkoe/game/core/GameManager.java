package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.custom.controls.GridButton;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.neoterux.sttkoe.utils.TableUtils.matrixIterFiller;

/**
 * This class has the responsibility to handler the game turn, and other aspects.
 */
public class GameManager {
    
    private static final Logger logger = LoggerFactory.getLogger("GameManager");
    
    private final GameMode gameMode;
    
    private GridButton ttbuttons[][];
    
    public GameManager(GameMode mode){
        this.gameMode = mode;
    }
    
    public void fillGrid(GridPane holder){
        logger.debug("Filling the holder {}", holder);
        ttbuttons = new GridButton[3][3];
        fillMatrix((button, row, col)->{
            holder.add(button,col,row);
        });
    }
    
    private void fillMatrix(FillAction<GridButton> action){
//        Random rd = new Random();
        logger.debug("filling matrix...");
        matrixIterFiller(ttbuttons,(row, col) ->{
            GridButton button = new GridButton();
            button.fillAllGrid();
            button.setOnAction(event->{
                logger.debug("Button clicked on row: {}, col: {}", row, col);
            });
            if (action!= null)
                action.doAction(button,row,col);
            return  button;
        });
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++) {
//                GridButton button = new GridButton();
//                ttbuttons[i][j] = button;
//                button.fillAllGrid();
////                if (rd.nextBoolean())
////                    button.setSymbol(Symbol.X);
//                int finalI = i;
//                int finalJ = j;
//                button.setOnAction((eh)-> System.out.println("Button clicked on row:" + finalI + ", col:" + finalJ));
//                if (action != null)
//                    action.doAction(button, i, j);
//
//            }
        
        logger.debug("End of filling");
    }
    
    
    private interface FillAction<T>{
        void doAction(T content, int row, int col);
    }
}
