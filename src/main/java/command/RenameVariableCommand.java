package command;

import interfaces.ITreeReplaceService;
import services.TreeReplaceService;
import interfaces.ICommand;
import interfaces.IInputParserService;

/**
 * A command to allow the user to safely rename a variable
 */
public final class RenameVariableCommand extends AbstractComamnd implements ICommand {

    private ITreeReplaceService replacementHelper;

    public RenameVariableCommand() {
        this.replacementHelper = new TreeReplaceService(outputService, writerService);
    }

    public RenameVariableCommand(ITreeReplaceService replacementHelper) {
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
        return input.matches("^renamevariable [^ ]+ [^ ]+ [^ ]+ [^ ]+$") || input.matches("^rv [^ ]+ [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Renames a variable and all variable uses
     *
     * @param input User input containing input file, variable
     *              name, new variable name, and output file
     */
    @Override
    public void execute(String input) {
        replacementHelper.replace(TreeReplaceService.ReplacementType.VARIABLE, parser.splitInput(IInputParserService.SplitType.RENAME, input));
    }
}
