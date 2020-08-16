package gui.window;

import gui.Controller;
import javafx.util.Pair;
import template.TemplateField;
import template.TemplateLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import template.TemplateMetadata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MergeCommandWindow extends CustomCommandWindow {

    private TemplateMetadata currentMetadata = null;
    private List<Label> metadataLabels;
    private List<TextField> metadataTextFields;
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
        this.mergeButton = new Button("Merge scripts");
        this.exitButton = new Button("Exit");
        this.mergeWith = new ComboBox<>();
        this.deduplicateOther = new CheckBox("Scramble names");
        this.metadataTextFields = new ArrayList<>();
        this.metadataLabels = new ArrayList<>();
        this.deduplicateOther.setSelected(false);

        List<String> templates = TemplateLoader.getAllTemplates();
        for(String template : templates) {
            mergeWith.getItems().add(template);
        }
        mergeWith.getItems().add("Select file...");
        mergeWith.getSelectionModel().selectFirst();
        if(mergeWith.getItems().contains("NZCP")) {
            mergeWith.getSelectionModel().select("NZCP");
        }

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

        mergeWith.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                applyChange(root);
            }
        });

        setupScene(stage, root, "Merge Scripts");
        applyChange(root);
    }

    private void applyChange(VBox root) {
        destroyExistingMetadataControls(root);
        String value = mergeWith.getValue();
        if(TemplateLoader.canLoadTemplateMetadataByName(value)) {
            currentMetadata = TemplateLoader.loadTemplateMetadataByName(value);
            createMetadataTextFields(root);
        }
    }

    private void destroyExistingMetadataControls(VBox root) {
        for(TextField textField : metadataTextFields) {
            root.getChildren().remove(textField);
        }
        for(Label label : metadataLabels) {
            root.getChildren().remove(label);
        }
        metadataTextFields.clear();
        metadataLabels.clear();
    }

    private void createMetadataTextFields(VBox root) {
        for(TemplateField field : currentMetadata.getFields()) {
            Label metadataLabel = new Label(field.getFieldName());
            metadataLabels.add(metadataLabel);
            root.getChildren().add(metadataLabel);
            if(field.getType().equalsIgnoreCase("text")) {
                TextField metadataTextfield = new TextField();
                metadataTextfield.setText(field.getDefaultValue());
                metadataTextFields.add(metadataTextfield);
                root.getChildren().add(metadataTextfield);
            } // no other supported types right now
        }
    }

    public String getMergeType() {
        return mergeWith.getValue();
    }

    public boolean dedupe() {
        return deduplicateOther.isSelected();
    }

    public List<Pair<String,String>> findMetadataValues() {
        List<Pair<String,String>> metadataValues = new ArrayList<>();
        int index = 0;
        for(TemplateField field : currentMetadata.getFields()) {
            if(field.getType().equalsIgnoreCase("text")) {
                String value = metadataTextFields.get(index).getText();
                metadataValues.add(new Pair<>(field.getDefaultValue(), value));
            }
            index++;
        }
        return metadataValues;
    }
}
