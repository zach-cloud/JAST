package command;

import interfaces.ITreeMergeService;
import services.TreeMergeService;
import interfaces.ICommand;
import interfaces.IInputParserService;

/**
 * A command to allow the user to merge code files
 */
public final class MergeCommand extends AbstractComamnd implements ICommand {

    private ITreeMergeService mergeHelper;

    public MergeCommand() {
        this.mergeHelper = new TreeMergeService(outputService, writerService);
    }

    public MergeCommand(ITreeMergeService mergeHelper) {
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
        return input.matches("^merge [^ ]+ [^ ]+ [^ ]+$") || input.matches("^m [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Merges code files
     *
     * @param input User input containing input files and output file
     */
    @Override
    public void execute(String input) {
        mergeHelper.merge(parser.splitInput(IInputParserService.SplitType.MERGE, input), false);
    }
}
