package com.neoterux.sttkoe.models.players;

/**
 * A listener when an {@link PlayerSequence} change player.
 */
@FunctionalInterface
public interface PlayerChangeListener {
    
    void doOnChange(Player currentPlayer);
}
