package com.neoterux.sttkoe.view.controllers;

import com.neoterux.sttkoe.game.GameTurnManager;
import com.neoterux.sttkoe.game.GameValidator;
import com.neoterux.sttkoe.game.Symbol;
import com.neoterux.sttkoe.game.core.GameManager;
import com.neoterux.sttkoe.game.core.GameMode;
import com.neoterux.sttkoe.utils.AppUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the main View
 *
 * @author Neoterux
 */
public class MainViewController implements Initializable {

    @FXML
    private ChoiceBox<GameMode> choicePlay;

    @FXML
    private Text txtSymbol;

    @FXML
    private Text txtSymbolOponent;

    @FXML
    private ChoiceBox<Symbol> symbolPlayer;

    @FXML
    private ChoiceBox<Symbol> symbolEnemy;
    
    @FXML
    private ImageView imgBackground;
    
    @FXML
    private Pane root;

//    public ChoiceBox<String> getChoicePlay() {
//        return choicePlay;
//    }

//    public ChoiceBox<String> getSymbolPlayer() {
//        return symbolPlayer;
//    }
//
//    public ChoiceBox<String> getSymbolEnemy() {
//        return symbolEnemy;
//    }
    Symbol playerSymbol(){
        return this.symbolPlayer.getValue();
    }
    
    Symbol enemySymbol() {
        return this.symbolEnemy.getValue();
    }
    
    private Window currentWindow() {
        return this.btnInicio.getScene().getWindow();
    }
    
    private Stage currentStage(){
        return (Stage) currentWindow();
    }

    @FXML
    private Button btnInicio;

    @FXML
    void ComenzarJuego(ActionEvent event) throws IOException {
        FXMLLoader loader = AppUtils.loaderFrom("view/game_view.fxml");
        GameMode selectedMode = choicePlay.getValue();
        GameManager cGamemanager = new GameManager(selectedMode);
        loader.setControllerFactory(cclass -> new GameViewController(cGamemanager, createsGameValidator()));
        
        GameTurnManager tman = selectedMode.turnManager;
        tman.getHomePlayer().setPlayerSymbol(playerSymbol());
        tman.getVisitorPlayer().setPlayerSymbol(enemySymbol());
    
        Stage gameWindow = new Stage();
        Scene scene = new Scene(loader.load());
        gameWindow.setScene(scene);
        gameWindow.initModality(Modality.APPLICATION_MODAL);
        gameWindow.initOwner(currentWindow());
        currentStage().hide();
        gameWindow.showAndWait();
        currentStage().show();
    }

    void metodoListener(ActionEvent comenzarJuego){
        System.out.println("hola mundo");
        symbolEnemy.setDisable(false);
        symbolPlayer.setDisable(false);
        System.out.println(txtSymbol);
        // txtSymbol.setDisable(false);
        // txtSymbolOponent.setDisable(false);
    }
    
    void interchangeSymbol(ActionEvent event){
        if (playerSymbol() == Symbol.X) {
            symbolEnemy.setValue(Symbol.O);
        } else {
            symbolEnemy.setValue(Symbol.X);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choicePlay.setItems(FXCollections.observableArrayList(GameMode.values()));
        var x = FXCollections.observableArrayList(Symbol.values());
        symbolPlayer.setItems(x);
        symbolEnemy.setItems(x);
        symbolPlayer.setOnAction(this::interchangeSymbol);
        choicePlay.setOnAction(this::metodoListener);
    }

    private GameValidator createsGameValidator(){
        GameValidator gm = new GameValidator();
        gm.setUserSymbol(playerSymbol().toString());
        gm.setComputerSymbol(enemySymbol().toString());
        return gm;
    }
}
