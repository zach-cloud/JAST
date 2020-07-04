package gui.components;

import gui.window.CompileResultsWindow;
import interfaces.IFileWriterService;
import interfaces.ISyntaxChecker;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import services.FileWriterService;
import services.SyntaxCheckerService;
import tree.SyntaxTree;

public class SyntaxCheckerComponent extends GenericComponent {

    private ISyntaxChecker syntaxCheckerService;
    private IFileWriterService writerService;

    public SyntaxCheckerComponent(ComponentContext context) {
        super(context);
        this.writerService = new FileWriterService();
        this.syntaxCheckerService = new SyntaxCheckerService(writerService);
    }

    public void syntaxCheck() {
        CompileResultsWindow resultWindow =
                new CompileResultsWindow(syntaxCheckerService,
                        SyntaxTree.readTree(context.jassCodeEditor.getText()));

        resultWindow.start(new Stage());
    }

}
