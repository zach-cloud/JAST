package gui;

import interfaces.ISyntaxChecker;
import interfaces.ISyntaxTree;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class SyntaxCheckResultWindow extends Application {

    private ISyntaxChecker syntaxCheckerService;
    private TextArea resultsBox;
    private ISyntaxTree tree;

    public SyntaxCheckResultWindow(ISyntaxChecker syntaxCheckerService, ISyntaxTree tree) {
        this.syntaxCheckerService = syntaxCheckerService;
        this.tree = tree;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.resultsBox = new TextArea();
        resultsBox.setText("Running syntax check. . .");
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
        setupScene(stage, root);
        stage.show();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    resultsBox.setText(syntaxCheckerService.syntaxCheck(tree));
                } catch (Exception ex) {
                    resultsBox.setText("An error occurred when trying to syntax check:\n" + ex.getMessage());
                }
            }
        });
    }

    private void setupScene(Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Syntax Check");
    }

}
