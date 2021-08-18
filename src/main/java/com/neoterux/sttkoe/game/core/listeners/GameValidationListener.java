package com.neoterux.sttkoe.game.core.listeners;

import com.neoterux.sttkoe.models.players.Player;

public interface GameValidationListener {
    void doOnWin (Player winner);
    
    void doOnTie();
}
