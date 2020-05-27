package command;

import interfaces.ITreeMergeService;
import services.TreeMergeService;
import interfaces.ICommand;
import interfaces.IInputParserService;
import settings.Settings;

/**
 * A command to allow the user to merge code files
 */
public final class JJCPDCommand extends AbstractComamnd implements ICommand {

    private ITreeMergeService mergeHelper;

    public JJCPDCommand() {
        this.mergeHelper = new TreeMergeService(outputService, writerService);
    }

    public JJCPDCommand(ITreeMergeService mergeHelper) {
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
        return Settings.CHEATING_ENABLED && input.matches("^jjcp-d [^ ]+ [^ ]+ [^ ]+$") || input.matches("^jjd [^ ]+ [^ ]+ [^ ]+$");
    }

    /**
     * Merges code files
     *
     * @param input User input containing input files and output file
     */
    @Override
    public void execute(String input) {
        mergeHelper.applyJjcp(true, parser.splitInput(IInputParserService.SplitType.CHEATPACK, input));
    }
}
