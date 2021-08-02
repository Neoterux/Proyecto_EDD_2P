package com.neoterux.sttkoe.game;

/**
 * This class holds the
 */
public abstract class GameTurnManager {


    public static class PvPManager extends GameTurnManager {

        public PvPManager() {
            super(1_000_000_000);
        }

    }

    public static class PvCManager extends GameTurnManager {

        public PvCManager() {
            super(750);
        }

    }

    public static class CPUManager extends GameTurnManager {

        public CPUManager() {
            super(1000);
        }

    }

    private GameTurnManager(long turnDelay) {

    }
}
