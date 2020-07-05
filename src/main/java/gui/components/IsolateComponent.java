package gui.components;

import gui.Controller;
import gui.window.IsolateWindow;
import interfaces.ISyntaxTree;
import nodes.AbstractFunction;
import nodes.functions.Argument;
import nodes.functions.Function;
import tree.SyntaxTree;

import java.util.ArrayList;
import java.util.List;

public final class IsolateComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private IsolateWindow isolateWindow;

    public IsolateComponent(ComponentContext context, StatusComponent statusComponent) {
        super(context);
        this.statusComponent = statusComponent;
    }

    public void isolate(Controller controller) {
        if (isolateWindow == null) {
            isolateWindow = new IsolateWindow(controller);
        }
        isolateWindow.show();
    }

    public void close() {
        if (isolateWindow != null) {
            isolateWindow.hide();
        }
    }

    private String isolateVariable(ISyntaxTree tree, String variableName) {
        // Find all functions that use this variable
        List<AbstractFunction> usageFunctions = new ArrayList<>();
        for (AbstractFunction function : tree.getScript()
                .getFunctionsSection().getFunctions()) {
            for (Argument argument : function.getArguments()) {
                if (argument.toString().equals(variableName)) {
                    usageFunctions.add(function);
                    break; // exit inner loop
                }
            }
        }
        return "";
    }

    private String isolateFunction(ISyntaxTree tree, String variableName) {
        return "";
    }

    public void runIsolate() {
        if (isolateWindow != null) {
            String type = isolateWindow.getIsolateType();
            String isolateText = isolateWindow.getIsolateText();
            String result = null;
            ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
            if (type.equalsIgnoreCase("global variable")) {
                result = isolateVariable(tree, isolateText);
            } else if (type.equalsIgnoreCase("function")) {
                result = isolateFunction(tree, isolateText);
            } else {
                throw new RuntimeException("Illegal type: " + type);
            }
            context.jassCodeEditor.replaceText(result);
        }
    }
}
