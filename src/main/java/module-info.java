/**
 * The tik tak toe game using trees and binary trees for search and other things.
 */
module SuperTikTakToe.main {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires logback.classic;
    requires org.slf4j;
    requires logback.core;

    exports com.neoterux.sttkoe;
    exports com.neoterux.sttkoe.view.controllers to javafx.fxml, javafx.graphics;

    opens com.neoterux.sttkoe to javafx.fxml, javafx.controls, logback.classic, org.slf4j, javafx.graphics;
    opens com.neoterux.sttkoe.view.controllers to javafx.fxml, logback.classic, org.slf4j, javafx.graphics;
}