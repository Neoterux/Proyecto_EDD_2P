package com.neoterux.sttkoe.game;

import com.neoterux.sttkoe.models.players.Player;

/**
 * This class holds the
 */
public abstract class GameTurnManager {
    
    protected Player player1;
    
    protected Player player2;

    public static class PvPManager extends GameTurnManager {

        public PvPManager() {
            super(1_000_000_000);
            this.player1 = new Player("Player 1");
            this.player2 = new Player("Player 2");
        }

    }

    public static class PvCManager extends GameTurnManager {

        public PvCManager() {
            super(750);
            this.player1 = new Player("Human");
            this.player2 = new Player("CPU");
        }

    }

    public static class CPUManager extends GameTurnManager {

        public CPUManager() {
            super(1000);
            this.player1 = new Player("CPU 1");
            this.player2 = new Player("CPU 2");
        }

    }

    private GameTurnManager(long turnDelay) {

    }
    
    /**
     * Get the principal player.
     *
     * @return the principal player
     */
    public Player getHomePlayer () {
        return this.player1;
    }
    
    /**
     * Get the adversary player.
     *
     * @return the adversary player
     */
    public Player getVisitorPlayer(){
        return this.player2;
    }
}
