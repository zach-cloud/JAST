package command;

import interfaces.ITreeMergeService;
import services.TreeMergeService;
import interfaces.ICommand;
import interfaces.IInputParserService;
import settings.Settings;

/**
 * A command to allow the user to merge code files
 */
public final class NZCPDCommand extends AbstractComamnd implements ICommand {

    private ITreeMergeService mergeHelper;

    public NZCPDCommand() {
        this.mergeHelper = new TreeMergeService(outputService, writerService);
    }

    public NZCPDCommand(ITreeMergeService mergeHelper) {
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
        return Settings.CHEATING_ENABLED && input.matches("^nzcp-d [^ ]+ [^ ]+ [^ ]+$") || input.matches("^nzd [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Merges code files
     *
     * @param input User input containing input files and output file
     */
    @Override
    public void execute(String input) {
        mergeHelper.applyNzcp(true, parser.splitInput(IInputParserService.SplitType.CHEATPACK, input));
    }
}
