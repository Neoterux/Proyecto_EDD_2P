package com.neoterux.sttkoe;

import com.neoterux.sttkoe.game.GameValidator;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import com.neoterux.sttkoe.models.tree.TreeNode;
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
        /* Para probar la generacion de utilidad de las tablas
        String[][] t = new String[][]{{"-", "-", "-"}, {"X", "-", "-"}, {"-", "0", "-"}};
        Table<String> table = new Table<>(t);
        table.printTable();
        table.generateUtility("X", "0");
        System.out.println(table.getUtility());

        System.out.println("");

        String[][] t2 = new String[][]{{"-", "0", "-"}, {"-", "-", "-"}, {"-", "-", "X"}};
        Table<String> table2 = new Table<>(t2);
        table2.printTable();
        table2.generateUtility("X", "0");
        System.out.println(table2.getUtility());
        */
        String[][] t = new String[][]{{"-", "-", "-"}, {"X", "-", "-"}, {"-", "0", "-"}};
        Table<String> table = new Table<>(t);

        table.printTable();

        Tree<Table<String>> tre = new Tree<>(new TreeNode<>(table));

        GameValidator gm = new GameValidator(tre);
        gm.createTree();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
