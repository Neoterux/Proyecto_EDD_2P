package com.neoterux.sttkoe.game;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>AI Thread</h1>
 * This class is a thread that holds the IA algorithm for the TikTakToe game.
 */
@Slf4j
public class AIThread extends Thread{

    /**
     * Creates a new AI thread
     */
    public AIThread(){
        log.info("New IA thread created.");
    }
}
