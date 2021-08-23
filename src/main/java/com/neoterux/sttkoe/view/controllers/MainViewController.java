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
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Controller for the main View
 *
 * @author Neoterux
 */
public class MainViewController implements Initializable {
    
    /**
     * The choice box that contains the Game modes
     */
    @FXML
    private ChoiceBox<GameMode> choicePlay;
    
    @FXML
    private Text txtSymbol;

    @FXML
    private Text txtSymbolOponent;
    
    /**
     * The choice box that contains the symbol for the first player
     */
    @FXML
    private ChoiceBox<Symbol> symbolPlayer;
    
    /**
     * The choice box that contains the symbol for the second player
     */
    @FXML
    private ChoiceBox<Symbol> symbolEnemy;
    
    /**
     * The background of the principal pane
     */
    @FXML
    private ImageView imgBackground;
    
    /**
     * The root pane of the stage.
     */
    @FXML
    private Pane root;
    
    /**
     * The button that starts the game
     */
    @FXML
    private Button btnInicio;
    private Symbol playerSymbol(){
        return this.symbolPlayer.getValue();
    }
    
    private Symbol enemySymbol() {
        return this.symbolEnemy.getValue();
    }
    
    /**
     * Gets the current window of the view.
     *
     * @return the windows of the view
     */
    private Window currentWindow() {
        return this.btnInicio.getScene().getWindow();
    }
    
    /**
     * Gets the current stage of this view
     *
     * @return the stage of this view.
     */
    private Stage currentStage(){
        return (Stage) currentWindow();
    }

    

    @FXML
    void ComenzarJuego(ActionEvent event) throws IOException {
        if(playerSymbol() == null || choicePlay.getValue() == null){
            Alert ventanaSalida = new Alert(Alert.AlertType.ERROR);
            ventanaSalida.setTitle("ERROR");
            ventanaSalida.setHeaderText(null);
            ventanaSalida.setContentText("Seleccione todos los campos para empezar el juego");
            ventanaSalida.initStyle(StageStyle.UTILITY);
            ventanaSalida.showAndWait();
            return;
        }
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
        symbolEnemy.setDisable(false);
        symbolPlayer.setDisable(false);
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
        gm.setUserSymbol(playerSymbol());
        gm.setComputerSymbol(enemySymbol());
        return gm;
    }
}
