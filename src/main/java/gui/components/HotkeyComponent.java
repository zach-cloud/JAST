package gui.components;

import gui.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public final class HotkeyComponent extends GenericComponent {

    public HotkeyComponent(ComponentContext context) {
        super(context);
    }

    public void setupHotkeys(Scene scene, Controller controller) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination openHotkey = new KeyCodeCombination(KeyCode.O,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination saveHotkey = new KeyCodeCombination(KeyCode.S,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination undoHotkey = new KeyCodeCombination(KeyCode.Z,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination redoHotkey = new KeyCodeCombination(KeyCode.Y,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination searchHotkey = new KeyCodeCombination(KeyCode.F,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination aboutHotkey = new KeyCodeCombination(KeyCode.F1);
            final KeyCombination syntaxCheckHotkey = new KeyCodeCombination(KeyCode.F9);
            final KeyCombination formatHotkey = new KeyCodeCombination(KeyCode.F8);
            final KeyCombination toggleFunctionBrowser = new KeyCodeCombination(KeyCode.F10);
            final KeyCombination autocomplete = new KeyCodeCombination(KeyCode.ENTER,
                    KeyCombination.CONTROL_DOWN);

            public void handle(KeyEvent ke) {
                if (openHotkey.match(ke)) {
                    controller.open(null);
                    ke.consume();
                } else if (saveHotkey.match(ke)) {
                    controller.saveAs(null);
                    ke.consume();
                } else if (undoHotkey.match(ke)) {
                    controller.undo(null);
                    ke.consume();
                } else if (redoHotkey.match(ke)) {
                    controller.redo(null);
                    ke.consume();
                } else if (searchHotkey.match(ke)) {
                    controller.search(null);
                    ke.consume();
                } else if (syntaxCheckHotkey.match(ke)) {
                    controller.syntaxCheck(null);
                    ke.consume();
                } else if (formatHotkey.match(ke)) {
                    controller.reformatCode(null);
                    ke.consume();
                } else if (aboutHotkey.match(ke)) {
                    controller.about(null);
                    ke.consume();
                } else if (toggleFunctionBrowser.match(ke)) {
                    controller.toggleFunctionBrowser();
                    ke.consume();
                } else if(autocomplete.match(ke)) {
                    controller.runAutocomplete();
                    ke.consume();
                }
            }
        });
    }
}
