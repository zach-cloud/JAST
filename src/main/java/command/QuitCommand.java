package command;

import interfaces.ICommand;
import interfaces.IExitProgramService;
import services.ExitProgramService;

/**
 * A command to allow the user to gracefully exit the program
 */
public final class QuitCommand extends AbstractComamnd implements ICommand {

    private IExitProgramService exitProgramService;

    public QuitCommand() {
        this.exitProgramService = new ExitProgramService();
    }

    public QuitCommand(IExitProgramService exitProgramService) {
        this.exitProgramService = exitProgramService;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return      True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.equals("quit") || input.equals("exit") || input.equals("q");
    }

    /**
     * Exits program
     *
     * @param input Ignored
     */
    @Override
    public void execute(String input) {
        exitProgramService.exit();
    }
}
