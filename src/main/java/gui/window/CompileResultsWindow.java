package gui.window;

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

public final class CompileResultsWindow extends CustomTextWindow {

    private ISyntaxChecker syntaxCheckerService;
    private TextArea resultsBox;
    private ISyntaxTree tree;

    public CompileResultsWindow(ISyntaxChecker syntaxCheckerService, ISyntaxTree tree) {
        this.syntaxCheckerService = syntaxCheckerService;
        this.tree = tree;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.resultsBox = new TextArea();
        resultsBox.setText("Running syntax check. . .");
        setupTextbox(resultsBox, stage, root);
        setupScene(stage, root, "Syntax check");
        stage.show();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    resultsBox.setText(syntaxCheckerService.syntaxCheck(tree));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultsBox.setText("An error occurred when trying to syntax check:\n" + ex.getMessage());
                }
            }
        });
    }

}
