package services;

import interfaces.IExitProgramService;

/**
 * Service to quit the program.
 */
public class ExitProgramServiceGUI implements IExitProgramService {

    /**
     * Exits the program.
     */
    public void exit() {
        System.exit(0);
    }
}
