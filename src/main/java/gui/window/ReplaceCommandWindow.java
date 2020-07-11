package gui.window;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class ReplaceCommandWindow extends CustomCommandWindow {

    private TextField originalText;
    private TextField replaceWithText;
    private ComboBox<String> renameType;
    private Button replaceButton;
    private Button exitButton;

    public ReplaceCommandWindow(Controller controller) {
        super(controller);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.originalText = new TextField();
        this.replaceWithText = new TextField();
        this.replaceButton = new Button("Replace");
        this.exitButton = new Button("Exit");
        this.renameType = new ComboBox<>();
        renameType.getItems().add("Global Variable");
        renameType.getItems().add("Function");
        renameType.getSelectionModel().selectFirst();
        root.getChildren().add(new Label("Original name: "));
        root.getChildren().add(originalText);
        root.getChildren().add(new Label("Replace with: "));
        root.getChildren().add(replaceWithText);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(renameType);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(replaceButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(exitButton);

        replaceButton.setOnAction(controller::runReplace);
        exitButton.setOnAction(controller::closeReplace);

        setupScene(stage, root, "Replace");
    }

    public String getOriginal() {
        return originalText.getText();
    }

    public String getReplaceWIth() {
        return replaceWithText.getText();
    }

    public String getSelectionType() {
        return renameType.getSelectionModel().getSelectedItem();
    }
}
