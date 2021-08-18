package com.neoterux.sttkoe.models.players;

/**
 * This class contains a fixed sequence of players, that can travel in loop.
 */
public class PlayerSequence {

    /**
     * The list of players that participates
     */
    private Player[] players;

    private final int MAX_PLAYERS;

    /**
     * The index of the current player.
     */
    private int currentPlayerPosition;
    
    /**
     * A listener when a player has changed
     */
    private PlayerChangeListener changeListener;

    /**
     * Creates a new sequence of the inserted players.
     *
     * @param players the players that would be used in the
     *
     * @throws IllegalArgumentException if inserted players are empty.
     */
    public PlayerSequence(Player... players){
        if (players.length == 0)
            throw new IllegalArgumentException("Players cannot be empty");
        this.players = players;
        this.MAX_PLAYERS = players.length;
        this.currentPlayerPosition = 0;
    }

    /**
     * Compare the current player by the given Player.
     *
     * @param comp the player to compare the current player.
     * @return true if the players are equals.
     * @see Player#equals(Object)
     */
    public boolean currentIs(Player comp){
        return getCurrent().equals(comp);
    }

    /**
     * Gets the current player.
     *
     * @return the current player.
     */
    public Player getCurrent() {
        return this.players[currentPlayerPosition];
    }

    /**
     * Update the position of the sequence, and return the next player.
     *
     * @return the next player
     */
    public Player nextPlayer() {
        this.currentPlayerPosition = ++currentPlayerPosition % MAX_PLAYERS;
        if (changeListener != null)
            changeListener.doOnChange(getCurrent());
        return getCurrent();
    }

    /**
     * Checks if the current player is a CPU.
     *
     * @return true if is cpu, else false
     * @see Player#isCpu()
     */
    public boolean currentIsCpu(){
        return getCurrent().isCpu();
    }
    
    /**
     * Seek the next player without generate a next Listener and change the internal state.
     *
     * @return the next player.
     */
    public Player seekNext() {
        int idx = this.currentPlayerPosition + 1;
        return players[idx % MAX_PLAYERS];
    }
    public void setChangeListener (PlayerChangeListener changeListener) {
        this.changeListener = changeListener;
    }
}
