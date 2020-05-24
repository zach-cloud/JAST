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

public class SyntaxCheckResultWindow extends Application {

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
        root.getChildren().add(resultsBox);

        setupScene(stage, root);
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                resultsBox.setText(syntaxCheckerService.syntaxCheck(tree));
            }
        });
    }

    private void setupScene(Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Syntax Check");
    }

}
