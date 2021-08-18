package com.neoterux.sttkoe.game.core.listeners;

import com.neoterux.sttkoe.game.core.GameControls;
import com.neoterux.sttkoe.models.players.Player;

public interface AiChangeDetectedListener {
    void doOnChange (GameControls ui, Player ai);
}
