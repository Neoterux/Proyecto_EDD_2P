package com.neoterux.sttkoe.view.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * Controller for the main View
 *
 * @author Neoterux
 */
public class MainViewController {

    @FXML
    private ChoiceBox<String> choicePlay = new ChoiceBox<>(FXCollections.observableArrayList(
            "Player vs Player",
            "Player vs CPU",
            "CPU vs CPU"));

    @FXML
    private ChoiceBox<String> symbolPlayer = new ChoiceBox<>(FXCollections.observableArrayList("X", "O"));

    @FXML
    private ChoiceBox<String> symbolEnemy = new ChoiceBox<>(FXCollections.observableArrayList("X", "O"));

    @FXML
    private Button btnInicio;

    @FXML
    void ComenzarJuego(ActionEvent event) {

    }
}
