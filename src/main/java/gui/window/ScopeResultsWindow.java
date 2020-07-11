package gui.window;

import interfaces.IAnalysisService;
import interfaces.ISyntaxTree;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nodes.j.Variable;

public final class ScopeResultsWindow extends CustomTextWindow {

    private IAnalysisService analysisService;
    private TextArea resultsBox;
    private ISyntaxTree tree;

    public ScopeResultsWindow(IAnalysisService analysisService, ISyntaxTree tree) {
        this.analysisService = analysisService;
        this.tree = tree;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.resultsBox = new TextArea();
        resultsBox.setText("Running scope report . .\n");
        setupTextbox(resultsBox, stage, root);
        setupScene(stage, root, "Scope report");
        stage.show();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    scopeReport(tree, resultsBox);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultsBox.setText("An error occurred when trying to syntax check:\n" + ex.getMessage());
                }
            }
        });
    }

    private void scopeReport(ISyntaxTree tree, TextArea resultsBox) {
        try {
            for (Variable variable : tree.getGlobalVariables()) {
                resultsBox.appendText("Finding scope for " + variable.getName());
                IAnalysisService.VariableScope scope = analysisService.findVariableScope(tree, variable.getName());
                if (scope == IAnalysisService.VariableScope.UNUSED) {
                    resultsBox.appendText(": unused\n");
                } else if (scope == IAnalysisService.VariableScope.SINGLE_USE) {
                    resultsBox.appendText(": used once\n");
                } else if (scope == IAnalysisService.VariableScope.GLOBAL) {
                    resultsBox.appendText(": used many times\n");
                }
            }
        } catch (Exception ex) {
            resultsBox.appendText("\nScope report crashed: " + ex.getMessage());
        }
    }

}
