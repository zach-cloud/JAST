package gui.components;

import gui.Controller;
import gui.window.StringHashCommandWindow;
import interfaces.IHashBreakService;
import interfaces.IHashService;
import model.InputModel;
import services.HashBreakService;
import services.HashService;


public final class StringHashComponent extends GenericComponent {

    private StringHashCommandWindow stringHashWindow;
    private IHashBreakService hashBreakService;
    private IHashService hashService;

    public StringHashComponent(ComponentContext context) {
        super(context);
        this.hashBreakService = new HashBreakService();
        this.hashService = new HashService();
    }

    public void computeStringhash(Controller controller) {
        if (stringHashWindow == null) {
            stringHashWindow = new StringHashCommandWindow(controller);
        }
        stringHashWindow.show();
    }

    public void calculateStringHash() {
        InputModel model = new InputModel();
        model.setPlaintext(stringHashWindow.getHashText());
        stringHashWindow.setResult(hashService.runHash(model));
    }

    public void breakStringhashExecute() {
        InputModel model = new InputModel();
        model.setHash(stringHashWindow.getHashText());
        hashBreakService.setHash(model);
        stringHashWindow.setResult(hashBreakService.runBreak());
    }

    public void closeStringHash() {
        if (stringHashWindow != null) {
            stringHashWindow.hide();
        }
    }
}
