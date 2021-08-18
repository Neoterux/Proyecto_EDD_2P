package com.neoterux.sttkoe.view.controllers;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.game.GameValidator;
import com.neoterux.sttkoe.game.core.GameControls;
import com.neoterux.sttkoe.game.core.GameManager;
import com.neoterux.sttkoe.game.core.listeners.AiChangeDetectedListener;
import com.neoterux.sttkoe.game.core.listeners.GameValidationListener;
import com.neoterux.sttkoe.models.players.Player;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameViewController implements Initializable {

    @FXML
    private Text txtModalidad;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private VBox vBoxModalidad;
    /**
     * The grid that hold the tik tak toe game.
     */
    @FXML
    private GridPane gameGrid;
    
    /**
     * The manager that would hold the turn logic.
     */
    private GameManager manager;
    private GameValidator gv;

    private Tree<Table> gameTree;
    private Table gameTable;
    
    /**
     * An interface to prevent interact directly from manager to the UI
     */
    private GameControls controls;

    public GameViewController(GameManager manager){
        this.manager = manager;
    }
    public GameViewController(GameManager manager, GameValidator gv){
        this.manager = manager;
        this.gv = gv;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Text modo = new Text(String.valueOf(main.getChoicePlay()));
//        txtModalidad.setText(txtModalidad.getText()+" "+modo.getText());
        String mg = manager.getGameMode().gameModeName;
        borderPane.setStyle("-fx-background-color:#272727");
        
        Text modalidad = new Text(String.valueOf(mg));
        modalidad.setFill(Color.WHITE);
        modalidad.setFont(new Font("Colonna MT",30));
        vBoxModalidad.getChildren().add(modalidad);
        
        // TODO: Create a label with the current player and pass into GameControls
        this.controls = new GameControls(gameGrid, null);
        //TODO: Lock Table, or do something to prevent playing on the Validation Listener.
        manager.setValidationListener(new GameValidationListener() {
            @Override
            public void doOnWin (Player winner) {
                //new Alert(Alert.AlertType.CONFIRMATION,"Ganó: " + winner.getName(), ButtonType.OK).show();
                Alert ventanaSalida = new Alert(Alert.AlertType.INFORMATION);
                ventanaSalida.setTitle("FELICIDADES!");
                ventanaSalida.setHeaderText(null);
                ventanaSalida.setContentText("Ganó: " + winner.getName());
                ventanaSalida.initStyle(StageStyle.UTILITY);
                ventanaSalida.showAndWait();
                
                ((Stage) gameGrid.getScene().getWindow()).close();
            }
    
            @Override
            public void doOnLoose () {
                
            }
    
            @Override
            public void doOnTie () {
                Alert ventanaSalida = new Alert(Alert.AlertType.INFORMATION);
                ventanaSalida.setTitle("FELICIDADES!");
                ventanaSalida.setHeaderText(null);
                ventanaSalida.setContentText("Es un empate. Mejor suerte para la proxima :p");
                ventanaSalida.initStyle(StageStyle.UTILITY);
                ventanaSalida.showAndWait();
                
                ((Stage) gameGrid.getScene().getWindow()).close();
            }
        });
        
        manager.setAiListener(new AiChangeDetectedListener() {
            @Override
            public void doOnChange(GameControls ui, Player ai) {
                GridPane gridPane = ui.getTable();
                gameTable = new Table(gridPane);
                gameTree = new Tree<>(new TreeNode<>(gameTable));
                gv.setTree(gameTree);
                gv.initializeTree();
                GridPane newGridPane = gv.getBestOption().getTable();

                int index = getIndex(newGridPane, gridPane);

                ui.selectButtonBy(ai, index/3, index - 3*(index/3));
                manager.forceNextPlayer();
            }
        });
        manager.fillGrid(gameGrid);
        manager.init(controls);
    }

    private int getIndex(GridPane newGP, GridPane GP){
        int index = 0;
        for (int i = 0; i < 9; i++) {
            GridButton GB = (GridButton) GP.getChildren().get(i);
            GridButton newGB = (GridButton) newGP.getChildren().get(i);
            if(GB.currentSymbol() != newGB.currentSymbol()){
                index = i;
            }
        }
        return index;
    }

    void metodoListener(ActionEvent comenzarJuego){
        System.out.println("hola mundo");

    }
}
