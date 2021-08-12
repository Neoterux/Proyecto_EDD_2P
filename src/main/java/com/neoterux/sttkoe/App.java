package com.neoterux.sttkoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.neoterux.sttkoe.utils.AppUtils.loadParent;

/**
 * <h1>App</h1>
 * <p>
 * This class contains the Principal Application class.
 */
public final class App extends Application {

    /**
     * The main stage of the application
     */
    private Stage rootStage;

    @Override
    public void start(Stage primaryStage) {
        this.rootStage = primaryStage;
        primaryStage.setTitle("Bienvenido al Super Tik Tak Toe");
        Parent rootParent = loadParent(getClass(), "view/main_view.fxml");
        if (rootParent == null) {
            throw new RuntimeException("The root pane of the application cannot be null");
        }

        Scene rootScene = new Scene(rootParent);
        primaryStage.setScene(rootScene);
        primaryStage.show();

        //Para probar la generacion de los futuros movimientos
        /*String[][] t = new String[][]{{"-", "-", "-"}, {"X", "-", "-"}, {"-", "0", "-"}};
        Table<String> table = new Table<>(t);

        Tree<Table<String>> tre = new Tree<>(new TreeNode<>(table));

        GameValidator gm = new GameValidator(tre);
        gm.setComputerSymbol("X");
        gm.setUserSymbol("0");
        gm.initializeTree();
        Table<String> tableFinal = gm.getBestOption().getRoot().getContent();
        tableFinal.printTable();
        System.out.println(tableFinal.getUtility());*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
