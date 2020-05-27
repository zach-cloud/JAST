package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class StringHashWindow extends Application {

    private Controller controller;
    private TextField hashText;
    private TextField resultText;
    private Button calculateStringhashButton;
    private Button breakStringhashButton;
    private Button exitButton;
    private Stage stage;

    public StringHashWindow(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.hashText = new TextField();
        this.resultText = new TextField();
        this.calculateStringhashButton = new Button("Calculate StringHash");
        this.breakStringhashButton = new Button("Break StringHash");
        this.exitButton = new Button("Exit");
        root.getChildren().add(new Label("Input: "));
        root.getChildren().add(hashText);
        root.getChildren().add(new Label("Output: "));
        root.getChildren().add(resultText);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(calculateStringhashButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(breakStringhashButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(exitButton);

        calculateStringhashButton.setOnAction(controller::calculateStringHash);
        breakStringhashButton.setOnAction(controller::breakStringhashExecute);
        exitButton.setOnAction(controller::closeStringHash);

        setupScene(controller, stage, root);
        stage.setHeight(300);
        stage.setWidth(300);
        stage.show();
    }

    private void setupScene(Controller controller, Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Search");
    }

    public void show() {
        if(this.stage == null) {
            this.stage = new Stage();
        }
        this.start(stage);
    }

    public void hide() {
        stage.hide();
    }

    public String getHashText() {
        return hashText.getText();
    }

    public void setResult(String result) {
        this.resultText.setText(result);
    }
}
