package command;

import interfaces.ITreeMergeService;
import services.TreeMergeService;
import interfaces.ICommand;
import interfaces.IInputParserService;

/**
 * A command to allow the user to merge code files
 */
public final class NZCPCommand extends AbstractComamnd implements ICommand {

    private ITreeMergeService mergeHelper;

    public NZCPCommand() {
        this.mergeHelper = new TreeMergeService(outputService, writerService);
    }

    public NZCPCommand(ITreeMergeService mergeHelper) {
        this.mergeHelper = mergeHelper;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return      True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^nzcp [^ ]+ [^ ]+ [^ ]+$") || input.matches("^nz [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Merges code files
     *
     * @param input User input containing input files and output file
     */
    @Override
    public void execute(String input) {
        mergeHelper.applyNzcp(false, parser.splitInput(IInputParserService.SplitType.CHEATPACK, input));
    }
}
