package com.neoterux.sttkoe.view.controllers;

import com.neoterux.sttkoe.game.GameValidator;
import com.neoterux.sttkoe.game.core.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML
    private Text txtModalidad;
    @FXML
    private GridPane gameGrid;

    MainViewController main;
    
    private GameManager manager;
    private GameValidator gm;

    public GameViewController(GameManager manager){
        this.manager = manager;
    }
    public GameViewController(GameManager manager, GameValidator gm){
        this.manager = manager;
        this.gm = gm;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(gm.getUserSymbol());
        System.out.println(gm.getComputerSymbol());
//        Text modo = new Text(String.valueOf(main.getChoicePlay()));
//        txtModalidad.setText(txtModalidad.getText()+" "+modo.getText());
    }

    void metodoListener(ActionEvent comenzarJuego){
        System.out.println("hola mundo");

    }
}
