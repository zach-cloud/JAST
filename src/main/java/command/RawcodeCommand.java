package command;

import interfaces.IFileWriterService;
import interfaces.IInputParserService;
import interfaces.IRawcodeService;
import model.InputModel;
import services.FileWriterService;
import services.RawcodeService;

public class RawcodeCommand extends AbstractComamnd {

    private IRawcodeService rawcodeService;

    public RawcodeCommand() {
        this.rawcodeService = new RawcodeService();
    }

    public RawcodeCommand(IRawcodeService rawcodeService) {
        this.rawcodeService = rawcodeService;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^rawcodes [^ ]+ [^ ]+.*$") || input.matches("^rc [^ ]+ [^ ]+.*$");
    }

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    @Override
    public void execute(String input) {
        print("Generating rawcodes");
        InputModel model = parser.splitInput(IInputParserService.SplitType.RAWCODE, input);
        if(model.getWtsFile() != null) {
            rawcodeService.addWTS(model.getWtsFile());
        }
        String rawcodes = rawcodeService.makeRawcodesFrom(model.getRawcodeInput());
        writerService.writeString(rawcodes, model.getRawcodeOutput());
        print("Done generating rawcodes");
    }
}
