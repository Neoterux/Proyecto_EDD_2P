package com.neoterux.sttkoe.game.core;

/**
 * This class has the responsibility to handler the game turn, and other aspects.
 */
public class GameManager {
    
    private final GameMode gameMode;
    
    public GameManager(GameMode mode){
        this.gameMode = mode;
    }
}
