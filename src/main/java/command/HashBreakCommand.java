package command;

import interfaces.IHashBreakService;
import services.HashBreakService;
import interfaces.ICommand;
import interfaces.IInputParserService;

public final class HashBreakCommand extends AbstractComamnd implements ICommand {

    private IHashBreakService helper;

    public HashBreakCommand() {
        this.helper = new HashBreakService();
    }

    public HashBreakCommand(IHashBreakService helper) {
        this.helper = helper;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^hashbreak [^ ]+$") || input.matches("^hb [^ ]+$");
    }

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    @Override
    public void execute(String input) {
        helper.setHash(parser.splitInput(IInputParserService.SplitType.HASHBREAK, input));
        helper.runBreak();
    }
}
