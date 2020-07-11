package gui.window;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public abstract class CustomTextWindow extends Application {

    protected void setupTextbox(TextArea resultsBox, Stage stage, VBox root) {
        resultsBox.setMinHeight(400);
        resultsBox.setMinWidth(600);
        resultsBox.setPrefHeight(400);
        resultsBox.setPrefWidth(600);
        root.setMinHeight(400);
        root.setMinWidth(600);
        root.setPrefHeight(400);
        root.setPrefWidth(600);
        stage.setHeight(400);
        stage.setWidth(600);
        root.getChildren().add(resultsBox);
    }

    protected void setupScene(Stage stage, VBox root, String title) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
    }
}
