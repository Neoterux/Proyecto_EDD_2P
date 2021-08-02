package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.game.GameTurnManager;

/**
 * This would hold the game mode.
 */
public enum GameMode {
    /**
     * Player vs player.
     */
    PVP("Player vs Player", new GameTurnManager.PvPManager()),
    /**
     * Player vs Computer.
     */
    PVC("Player vs CPU", new GameTurnManager.PvCManager()),
    /**
     * Computer vs Computer.
     */
    CVC("CPU vs CPU", new GameTurnManager.CPUManager());

    /**
     * The game mode String representation.
     */
    public final String gameModeName;

    /**
     * The game turn manager.
     */
    public final GameTurnManager turnManager;

    GameMode(String name, GameTurnManager manager) {
        this.gameModeName = name;
        this.turnManager = manager;
    }
}
