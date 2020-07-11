package gui.window;

import gui.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import settings.Settings;

public final class MergeCommandWindow extends CustomCommandWindow {

    private TextField activator;
    private ComboBox<String> mergeWith;
    private CheckBox deduplicateOther;
    private Button mergeButton;
    private Button exitButton;

    public MergeCommandWindow(Controller controller) {
        super(controller);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.activator = new TextField();
        this.mergeButton = new Button("Merge scripts");
        this.exitButton = new Button("Exit");
        this.mergeWith = new ComboBox<>();
        this.deduplicateOther = new CheckBox("Scramble names");
        this.deduplicateOther.setSelected(true);
        if(Settings.CHEATING_ENABLED) {
            mergeWith.getItems().add("JJCP");
            mergeWith.getItems().add("NZCP");
            root.getChildren().add(new Label("Custom activator: "));
            root.getChildren().add(activator);
            this.activator.setText("wc3edit");
        }
        mergeWith.getItems().add("Select file...");
        mergeWith.getSelectionModel().selectFirst();
        mergeWith.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String value = mergeWith.getValue();
                if(value.equalsIgnoreCase("jjcp")) {
                    activator.setText("wc3edit");
                } else if(value.equalsIgnoreCase("nzcp")) {
                    activator.setText("easymode");
                }
            }
        });

        root.getChildren().add(new Label("Merge with:"));
        root.getChildren().add(mergeWith);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(deduplicateOther);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(mergeButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(exitButton);

        mergeButton.setOnAction(controller::runMerge);
        exitButton.setOnAction(controller::closeMerge);

        setupScene(stage, root, "Merge Scripts");
    }

    public String getMergeType() {
        return mergeWith.getValue();
    }

    public String getActivator() {
        return activator.getText();
    }

    public boolean dedupe() {
        return deduplicateOther.isSelected();
    }

}
