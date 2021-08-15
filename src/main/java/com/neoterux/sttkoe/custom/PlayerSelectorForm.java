package com.neoterux.sttkoe.custom;

import com.neoterux.sttkoe.models.players.Player;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;

public class PlayerSelectorForm {

    public PlayerSelectorForm() {
        Dialog<Player> playerDialog = new ChoiceDialog<>();
    }
}
