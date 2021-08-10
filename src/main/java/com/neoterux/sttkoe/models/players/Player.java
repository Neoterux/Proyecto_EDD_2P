package com.neoterux.sttkoe.models.players;

import com.neoterux.sttkoe.game.Symbol;

/**
 * This class represents a player and its symbol.
 */
public final class Player {
    
    private final String name;
    
    private Symbol playerSymbol;
    
    public Player(String name, Symbol symbol){
        this.name = name;
        this.playerSymbol = symbol;
    }
    public Player(String name){
        this(name, null);
    }
    
    public String getName () {
        return name;
    }
    
    public Symbol getPlayerSymbol () {
        return playerSymbol;
    }
    
    public void setPlayerSymbol (Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }
    
    @Override
    public String toString () {
        return "Player{" +
                "name='" + name + '\'' +
                ", playerSymbol=" + playerSymbol +
                '}';
    }
}
