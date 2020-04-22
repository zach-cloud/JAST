package command;

import services.FileWriterService;
import services.InputParserService;
import services.OutputService;
import interfaces.ICommand;
import interfaces.IFileWriterService;
import interfaces.IInputParserService;
import interfaces.IOutputService;

public abstract class AbstractComamnd implements ICommand {

    /**
     * Used to print to the user
     */
    protected IOutputService outputService;
    /**
     * Used to write syntax trees out
     */
    protected IFileWriterService writerService;
    /**
     * Parses user input into usable form
     */
    protected IInputParserService parser;

    /**
     * Creates a new Abstract Statement with an output service
     */
    public AbstractComamnd() {
        this.outputService = new OutputService();
        this.writerService = new FileWriterService();
        this.parser = new InputParserService();
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return      True if matches command; false if not.
     */
    public abstract boolean isCommend(String input);

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    public abstract void execute(String input);

    protected void print(String output) {
        outputService.print(output);
    }

    /**
     * For tests
     *
     * @param outputService Mock output service
     */
    protected void setOutputService(IOutputService outputService) {
        this.outputService = outputService;
    }

    /**
     * For tests
     *
     * @param writerService Mock writer service
     */
    protected void setWriterService(IFileWriterService writerService) {
        this.writerService = writerService;
    }
}
