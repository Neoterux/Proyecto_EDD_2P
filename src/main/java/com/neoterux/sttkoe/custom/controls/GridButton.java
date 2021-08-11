package com.neoterux.sttkoe.custom.controls;

import com.neoterux.sttkoe.custom.data.ObservableData;
import com.neoterux.sttkoe.game.Symbol;
import com.neoterux.sttkoe.models.players.Player;
import javafx.scene.control.Button;

/**
 * <h1>Grid Button</h1>
 * This is the button used into the grid of the tik tak toe.
 */
public class GridButton extends Button {
    
    /**
     * The symbol that hold the button
     */
    private ObservableData<Symbol> currentSymbol;
    
    /**
     * The associated player to the button. This can be optional.
     */
    private Player asociatedPlayer;
    
    /**
     * Creates a new Button with empty symbol
     */
    public GridButton(){
        currentSymbol = new ObservableData<>(null);
        configureSymbolListener();
    }
    
    /**
     * Add the listener to react when the symbol is placed, it will be written.
     */
    private void configureSymbolListener() {
        currentSymbol.subscribe((oldSymbol, newSymbol)->{
            setText(newSymbol.name());
        });
    }
    
    public Symbol currentSymbol() {
        return currentSymbol.get();
    }
    
    /**
     * Check if the button is blank
     * @return
     */
    public boolean isBlank() {
        return currentSymbol() == null;
    }
    
    /**
     * Set the Symbol to the button.
     *
     * @param symbol the symbol to write into the button
     * @return true if the symbol can be written, only if is blank.
     */
    public boolean setSymbol(Symbol symbol){
        if(symbol == null || !isBlank())
            return false;
        
        currentSymbol.set(symbol);
        return true;
    }
    
    /**
     * Set the associated Player.
     *
     * @param associatedPlayer the player that would be associated to the button.
     */
    public void setAsociatedPlayer (Player associatedPlayer) {
        this.asociatedPlayer = asociatedPlayer;
    }
    
    /**
     * Get the current asociated player.
     *
     * @return the asociated player.
     */
    public Player getAsociatedPlayer () {
        return asociatedPlayer;
    }
}

