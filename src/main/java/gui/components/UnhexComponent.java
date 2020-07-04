package gui.components;

import interfaces.ISyntaxTree;
import interfaces.IUnhexService;
import javafx.event.ActionEvent;
import services.UnhexService;
import tree.SyntaxTree;

public class UnhexComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private RefactorComponent refactorComponent;
    private IUnhexService unhexService;

    public UnhexComponent(ComponentContext context, StatusComponent statusComponent, RefactorComponent refactorComponent) {
        super(context);
        this.statusComponent = statusComponent;
        this.refactorComponent = refactorComponent;
        this.unhexService = new UnhexService();
    }

    public void unhex() {
        long time = System.currentTimeMillis();
        ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
        unhexService.unhex(tree);
        context.jassCodeEditor.replaceText(tree.toString());
        refactorComponent.formatIfDesired();
        time = time - System.currentTimeMillis();
        statusComponent.changeStatus("Unhexed code", time);
    }
}
