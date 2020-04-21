package command;

import interfaces.IHashService;
import services.HashService;
import interfaces.ICommand;
import interfaces.IInputParserService;

public final class HashCommand extends AbstractComamnd implements ICommand {

    private IHashService hashService;

    public HashCommand() {
        this.hashService = new HashService();
    }

    public HashCommand(IHashService service) {
        this.hashService = service;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^hash [^ ]+$") || input.matches("^h [^ ]+$");
    }

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    @Override
    public void execute(String input) {
        hashService.runHash(parser.splitInput(IInputParserService.SplitType.HASH, input));
    }
}
