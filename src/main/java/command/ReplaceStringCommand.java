package command;

import interfaces.ITreeReplaceService;
import services.TreeReplaceService;
import interfaces.ICommand;
import interfaces.IInputParserService;

/**
 * Replaces a String from the file with a new string
 */
public class ReplaceStringCommand extends AbstractComamnd implements ICommand {

    private ITreeReplaceService replacementHelper;

    public ReplaceStringCommand() {
        this.replacementHelper = new TreeReplaceService(outputService, writerService);
    }

    public ReplaceStringCommand(ITreeReplaceService replacementHelper) {
        this.replacementHelper = replacementHelper;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^replacestring [^ ]+ [^ ]+ [^ ]+ [^ ]+$") || input.matches("^rs [^ ]+ [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    @Override
    public void execute(String input) {
        replacementHelper.replace(TreeReplaceService.ReplacementType.STRING, parser.splitInput(IInputParserService.SplitType.RENAME, input));
    }
}
