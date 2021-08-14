package com.neoterux.sttkoe.models.players;

import com.neoterux.sttkoe.game.Symbol;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents a player and its symbol.
 */
@Getter
@ToString
public final class Player {
    
    private final String name;
    
    @Setter
    private Symbol playerSymbol;
    
    public Player(String name, Symbol symbol){
        this.name = name;
        this.playerSymbol = symbol;
    }
    public Player(String name){
        this(name, null);
    }
    
    public boolean isCpu() {
        return this.name.trim().toLowerCase().startsWith("cpu");
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof Player){
            Player p = (Player) obj;
            return p.name.equals(this.name);
        }
        return false;
    }
}
