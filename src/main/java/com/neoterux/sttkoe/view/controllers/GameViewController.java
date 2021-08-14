package com.neoterux.sttkoe.view.controllers;

import com.neoterux.sttkoe.game.GameValidator;
import com.neoterux.sttkoe.game.core.GameManager;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeNode;
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
    
    /**
     * The grid that hold the tik tak toe game.
     */
    @FXML
    private GridPane gameGrid;
    
    /**
     * The manager that would hold the turn logic.
     */
    private GameManager manager;
    private GameValidator gm;

    private Tree<Table> gameTree;
    private Table gameTable;

    public GameViewController(GameManager manager){
        this.manager = manager;
    }
    public GameViewController(GameManager manager, GameValidator gm){
        this.manager = manager;
        this.gm = gm;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Text modo = new Text(String.valueOf(main.getChoicePlay()));
//        txtModalidad.setText(txtModalidad.getText()+" "+modo.getText());
        manager.fillGrid(gameGrid);

        gameTable = new Table(gameGrid);
        gameTree = new Tree<>(new TreeNode<>(gameTable));
    }

    void metodoListener(ActionEvent comenzarJuego){
        System.out.println("hola mundo");

    }
}
