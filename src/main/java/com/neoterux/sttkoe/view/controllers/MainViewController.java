package com.neoterux.sttkoe.view.controllers;

import com.neoterux.sttkoe.utils.AppUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the main View
 *
 * @author Neoterux
 */
public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<String> choicePlay;

    @FXML
    private Text txtSymbol;

    @FXML
    private Text txtSymbolOponent;

    @FXML
    private ChoiceBox<String> symbolPlayer;

    @FXML
    private ChoiceBox<String> symbolEnemy;

    public ChoiceBox<String> getChoicePlay() {
        return choicePlay;
    }

    public ChoiceBox<String> getSymbolPlayer() {
        return symbolPlayer;
    }

    public ChoiceBox<String> getSymbolEnemy() {
        return symbolEnemy;
    }

    @FXML
    private Button btnInicio;

    @FXML
    void ComenzarJuego(ActionEvent event) {
        FXMLLoader loader = AppUtils.loaderFrom("view/game_view.fxml");
    }

    void metodoListener(ActionEvent comenzarJuego){
        System.out.println("hola mundo");
        symbolEnemy.setDisable(false);
        symbolPlayer.setDisable(false);
        txtSymbol.setDisable(false);
        txtSymbolOponent.setDisable(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choicePlay.setItems(FXCollections.observableArrayList(
                "Player vs Player",
                "Player vs CPU",
                "CPU vs CPU"));
        symbolPlayer.setItems(FXCollections.observableArrayList("X", "O"));
        symbolEnemy.setItems(FXCollections.observableArrayList("X", "O"));
        choicePlay.setOnAction(this::metodoListener);
    }
}
