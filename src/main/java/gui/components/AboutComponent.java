package gui.components;

import javax.swing.*;

public final class AboutComponent extends GenericComponent {

    public AboutComponent(ComponentContext context) {
        super(context);
    }

    public void about() {
        JOptionPane.showMessageDialog(
                null,
                "JAST - Managed JASS Code Modifier\n" +
                        "Open source: https://github.com/zach-cloud/JAST\n" +
                        "Hotkey shortcuts:\n" +
                        "F1: About\n" +
                        "F8: Format code\n" +
                        "F9: Syntax check\n" +
                        "F10: Toggle function browser\n" +
                        "Ctrl + Enter: Autocomplete\n" +
                        "Default file hotkeys (Ctrl+O/S/F/V/Z/Y/X/C)");
    }
}
