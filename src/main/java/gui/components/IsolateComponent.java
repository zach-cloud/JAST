package gui.components;

import gui.Controller;
import gui.window.IsolateWindow;
import interfaces.IAnalysisService;
import interfaces.ISyntaxTree;
import model.IsolateResult;
import services.AnalysisService;
import tree.SyntaxTree;

import javax.swing.*;

import static interfaces.IAnalysisService.ExpansionStyle.*;


public final class IsolateComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private IsolateWindow isolateWindow;
    private IAnalysisService analysisService;

    public IsolateComponent(ComponentContext context, StatusComponent statusComponent) {
        super(context);
        this.statusComponent = statusComponent;
        this.analysisService = new AnalysisService();
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

    public void runIsolate() {
        if (isolateWindow != null) {
            try {
                String type = isolateWindow.getIsolateType();
                String isolateText = isolateWindow.getIsolateText();
                String iterationCount = isolateWindow.getIterationCount();
                String isolateStyle = isolateWindow.getIsolateStyle();
                if(isolateStyle.equalsIgnoreCase("Passive")) {
                    analysisService.setExpansionStyle(PASSIVE);
                } else if(isolateStyle.equalsIgnoreCase("Aggressive")) {
                    analysisService.setExpansionStyle(AGGRESSIVE);
                } else if(isolateStyle.equalsIgnoreCase("Inverse")) {
                    analysisService.setExpansionStyle(INVERSE);
                } else {
                    throw new RuntimeException("Illegal style: " + isolateStyle);
                }
                int iterationCountNumber = Integer.parseInt(iterationCount);
                IsolateResult result = null;
                ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
                if (type.equalsIgnoreCase("global variable")) {
                    result = analysisService.isolateVariable(tree, isolateText, iterationCountNumber);
                } else if (type.equalsIgnoreCase("function")) {
                    result = analysisService.isolateFunction(tree, isolateText, iterationCountNumber);
                } else {
                    throw new RuntimeException("Illegal type: " + type);
                }
                context.jassCodeEditor.replaceText(SyntaxTree.from(result).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number for iteration count");
            }

        }
    }
}
