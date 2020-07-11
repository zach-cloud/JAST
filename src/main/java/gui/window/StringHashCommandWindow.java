package gui.window;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class StringHashCommandWindow extends CustomCommandWindow {

    private TextField hashText;
    private TextField resultText;
    private Button calculateStringhashButton;
    private Button breakStringhashButton;
    private Button exitButton;

    public StringHashCommandWindow(Controller controller) {
        super(controller);
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

        setupScene(stage, root, "String Hash");
    }


    public String getHashText() {
        return hashText.getText();
    }

    public void setResult(String result) {
        this.resultText.setText(result);
    }
}
