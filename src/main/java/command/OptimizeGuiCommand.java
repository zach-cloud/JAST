package command;

import interfaces.IFileWriterService;
import interfaces.IGuiOptimizerService;
import interfaces.IInputParserService;
import interfaces.ISyntaxTree;
import model.InputModel;
import services.FileWriterService;
import services.GuiOptimizerService;

public class OptimizeGuiCommand extends AbstractComamnd {

    private IGuiOptimizerService optimizerService;
    private IFileWriterService writerService;

    /**
     * Creates a new Abstract Statement with an output service
     */
    public OptimizeGuiCommand() {
        super();
        this.optimizerService = new GuiOptimizerService();
        this.writerService = new FileWriterService();
    }

    public OptimizeGuiCommand(IGuiOptimizerService optimizerService, IFileWriterService writerService) {
        this.optimizerService = optimizerService;
        this.writerService = writerService;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^optimize [^ ]+ [^ ]+$") || input.matches("^opt [^ ]+ [^ ]+$");
    }

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    @Override
    public void execute(String input) {
        print("Running GUI optimizer");
        InputModel model = parser.splitInput(IInputParserService.SplitType.OPTIMIZE, input);
        ISyntaxTree tree = optimizerService.runOptimize(model);
        writerService.write(tree, model.getOutputPath());
        print("Optimization complete.");
    }
}
