package gui.window;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class IsolateWindow extends CustomWindow {

    private TextField toIsolate;
    private ComboBox<String> isolateType;
    private Button isolateButton;
    private Button exitButton;

    public IsolateWindow(Controller controller) {
        super(controller);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.toIsolate = new TextField();
        this.isolateButton = new Button("Isolate");
        this.exitButton = new Button("Exit");
        this.isolateType = new ComboBox<>();
        isolateType.getItems().add("Global Variable");
        isolateType.getItems().add("Function");
        isolateType.getSelectionModel().selectFirst();
        root.getChildren().add(new Label("Name to isolate: "));
        root.getChildren().add(toIsolate);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(isolateType);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(isolateButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(exitButton);

        isolateButton.setOnAction(controller::runIsolate);
        exitButton.setOnAction(controller::closeIsolate);

        setupScene(stage, root, "Isolate");
    }

    public String getIsolateText() {
        return toIsolate.getText();
    }

    public String getIsolateType() {
        return isolateType.getSelectionModel().getSelectedItem();
    }
}
