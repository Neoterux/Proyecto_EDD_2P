package com.neoterux.sttkoe.view.controllers;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.game.GameValidator;
import com.neoterux.sttkoe.game.core.GameControls;
import com.neoterux.sttkoe.game.core.GameManager;
import com.neoterux.sttkoe.game.core.GameMode;
import com.neoterux.sttkoe.game.core.listeners.GameValidationListener;
import com.neoterux.sttkoe.models.players.Player;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "GameViewController")
public class GameViewController implements Initializable {

    @FXML
    private Text txtModalidad;


    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    
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
        log.debug("creating GameViewController");
        this.manager = manager;
        this.gv = gv;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boolean isCpuVsCpu = manager.getGameMode() == GameMode.CVC;
        final boolean[] existsWinner = {false};
        log.debug("Playing on Cpu vs Cpu? {}", isCpuVsCpu);
        Random rd = new Random();
        String mg = manager.getGameMode().gameModeName;
        borderPane.setStyle("-fx-background-color:#272727");
        
        Text modalidad = new Text(String.valueOf(mg));
        modalidad.setFill(Color.WHITE);
        modalidad.setFont(new Font("Colonna MT",30));
        vBoxModalidad.getChildren().add(modalidad);
        
        // TODO: Create a label with the current player and pass into GameControls
        this.controls = new GameControls(gameGrid, null);

        manager.setValidationListener(new GameValidationListener() {
            @Override
            public void doOnWin (Player winner) {
                Alert ventanaSalida = new Alert(Alert.AlertType.INFORMATION);
                ventanaSalida.setTitle("FELICIDADES!");
                ventanaSalida.setHeaderText(null);
                ventanaSalida.setContentText("Gano: " + winner.getName());
                ventanaSalida.initStyle(StageStyle.UTILITY);
                ventanaSalida.showAndWait();
                existsWinner[0] = true;
                
                ((Stage) gameGrid.getScene().getWindow()).close();
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
        
        manager.setAiListener((ui, ai) -> {
            log.debug("THE BASILISK IS MOVING...");
            if(existsWinner[0])
                return;
            ui.lock();
            executor.schedule(() ->{

                GridPane gridPane = ui.getTable();
                gameTable = new Table(gridPane);
                gameTree = new Tree<>(new TreeNode<>(gameTable));
                gv.setTree(gameTree);
                gv.initializeTree();
                GridPane newGridPane = gv.getBestOption().getTable();

                int index = getIndex(newGridPane, gridPane);
                Platform.runLater(()-> {
                    ui.selectButtonBy(ai, index/3, index - 3*(index/3));
                    manager.forceNextPlayer();
                    if(!isCpuVsCpu) ui.unlock();
                    log.debug("FINISHING BASILISK MOVE...");
                });


            },500 + rd.nextInt(100) , TimeUnit.MILLISECONDS);
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
