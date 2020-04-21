package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class GUILauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My First JavaFX App");

        Label label = new Label("Hello World from JavaFX!");

        primaryStage.show();
    }
}
