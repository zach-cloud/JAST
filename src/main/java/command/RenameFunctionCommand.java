package command;

import interfaces.ITreeReplaceService;
import services.TreeReplaceService;
import interfaces.ICommand;
import interfaces.IInputParserService;

/**
 * A command to allow the user to safely rename a function
 */
public final class RenameFunctionCommand extends AbstractComamnd implements ICommand {

    private ITreeReplaceService replacementHelper;

    public RenameFunctionCommand() {
        this.replacementHelper = new TreeReplaceService(outputService, writerService);
    }

    public RenameFunctionCommand(ITreeReplaceService replacementHelper) {
        this.replacementHelper = replacementHelper;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return      True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^renamefunction [^ ]+ [^ ]+ [^ ]+ [^ ]+$") || input.matches("^rf [^ ]+ [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Renames a function and all function uses
     *
     * @param input User input containing input file, function
     *              name, new function name, and output file
     */
    @Override
    public void execute(String input) {
        replacementHelper.replace(TreeReplaceService.ReplacementType.FUNCTION, parser.splitInput(IInputParserService.SplitType.RENAME, input));
    }
}
