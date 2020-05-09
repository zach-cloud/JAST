package cli;

import gui.GUILauncher;

/**
 * Launches the program and calls command.
 */
public final class Launcher {

    /**
     * Launch the program.
     *
     * @param args  args[0] is "gui" if gui is desired
     */
    public static void main(String[] args) {
        if(args.length == 1 && args[0].equalsIgnoreCase("gui")) {
            GUILauncher.main(args);
        } else {
            new JAST().run();
        }
    }

}
