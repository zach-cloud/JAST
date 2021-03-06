package gui.window;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class IsolateCommandWindow extends CustomCommandWindow {

    private TextField toIsolate;
    private TextField iterationCount;
    private ComboBox<String> isolateType;
    private ComboBox<String> isolateStyle;
    private Button isolateButton;
    private Button exitButton;

    public IsolateCommandWindow(Controller controller) {
        super(controller);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.toIsolate = new TextField();
        this.isolateButton = new Button("Isolate");
        this.iterationCount = new TextField();
        this.exitButton = new Button("Exit");
        this.isolateType = new ComboBox<>();
        isolateType.getItems().add("Global Variable");
        isolateType.getItems().add("Function");
        isolateType.getSelectionModel().selectFirst();
        this.isolateStyle = new ComboBox<>();
        isolateStyle.getItems().add("Passive");
        isolateStyle.getItems().add("Aggressive");
        isolateStyle.getItems().add("Inverse");
        isolateStyle.getSelectionModel().selectFirst();
        root.getChildren().add(new Label("Name to isolate: "));
        root.getChildren().add(toIsolate);
        root.getChildren().add(new Label("Depth: "));
        root.getChildren().add(iterationCount);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(new Label("Isolate type: "));
        root.getChildren().add(isolateType);
        root.getChildren().add(new Label("Isolate style: "));
        root.getChildren().add(isolateStyle);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(isolateButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(exitButton);

        isolateButton.setOnAction(controller::runIsolate);
        exitButton.setOnAction(controller::closeIsolate);

        setupScene(stage, root, "Isolate");
    }

    public String getIterationCount() {
        return iterationCount.getText();
    }

    public String getIsolateText() {
        return toIsolate.getText();
    }

    public String getIsolateType() {
        return isolateType.getSelectionModel().getSelectedItem();
    }

    public String getIsolateStyle() {
        return isolateStyle.getSelectionModel().getSelectedItem();
    }
}
