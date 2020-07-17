package command;

import interfaces.IInputParserService;
import model.InputModel;
import mpq.MpqEditor;

import java.io.File;

public class ImportCommand extends AbstractComamnd {

    private MpqEditor editor;

    public ImportCommand() {
        this.editor = null;
    }

    public ImportCommand(MpqEditor editor) {
        this.editor = editor;
    }

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.matches("^import [^ ]+ [^ ]+$") || input.matches("^imp [^ ]+ [^ ]+$");
    }

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    @Override
    public void execute(String input) {
        InputModel model = parser.splitInput(IInputParserService.SplitType.MPQEDIT, input);
        if(editor == null) {
            editor = new MpqEditor(new File(model.getInputMpq()));
        }
    }
}
