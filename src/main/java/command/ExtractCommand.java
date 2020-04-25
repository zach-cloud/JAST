package command;

import interfaces.IInputParserService;
import model.InputModel;
import mpq.MpqEditor;

import java.io.File;

public class ExtractCommand extends AbstractComamnd {

    private MpqEditor editor;
    
    @Override
    public boolean isCommend(String input) {
        return input.matches("^export [^ ]+ [^ ]+$") || input.matches("^exp [^ ]+ [^ ]+$")|| input.matches("^ext [^ ]+ [^ ]+$")|| input.matches("^extract [^ ]+ [^ ]+$");
    }

    @Override
    public void execute(String input) {
        InputModel model = parser.splitInput(IInputParserService.SplitType.MPQEDIT, input);
        editor = new MpqEditor(new File(model.getInputMpq()));
        editor.extractFiles(new File(model.getOutputDirectory()));
    }

}
