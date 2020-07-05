package gui.window;

import gui.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class CustomWindow extends Application {

    private Stage stage;
    protected Controller controller;

    public CustomWindow(Controller controller) {
        this.controller = controller;
    }

    public void show() {
        try {
            if (this.stage == null) {
                this.stage = new Stage();
            }
            this.start(stage);
        } catch (Exception ex) {
            throw new RuntimeException("Could not start window: " + ex.getMessage());
        }
    }

    void setupScene(Stage stage, VBox root, String title) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setHeight(300);
        stage.setWidth(300);
        stage.show();
    }

    public void hide() {
        stage.hide();
    }
}
