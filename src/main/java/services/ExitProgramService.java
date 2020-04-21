package services;

import interfaces.IExitProgramService;
import cli.JASTState;

/**
 * Service to quit the program.
 */
public class ExitProgramService implements IExitProgramService {

    /**
     * Exits the program.
     */
    public void exit() {
        JASTState.quitDesired = true;
    }
}
