package gui;

import interfaces.IExitProgramService;
import javafx.event.ActionEvent;
import services.ExitProgramServiceGUI;

public class Controller {

    enum OpenType {
        MPQ,
        SCRIPT,
        TEXTFILE,
        OBJECTSFILE
    }

    private IExitProgramService exitProgramService;

    public Controller() {
        this.exitProgramService = new ExitProgramServiceGUI();
    }

    public void open(ActionEvent e) {

    }

    public void save(ActionEvent e) {

    }

    public void close(ActionEvent e) {
        exitProgramService.exit();
    }

    public void addNzcp(ActionEvent e) {

    }

    public void addJjcp(ActionEvent e) {

    }

    public void addNzcpD(ActionEvent e) {

    }

    public void addJjcpD(ActionEvent e) {

    }

    public void mergeScript(ActionEvent e) {

    }

    public void mergeScriptD(ActionEvent e) {

    }

    public void renameScriptVariable(ActionEvent e) {

    }

    public void renameScriptFunction(ActionEvent e) {

    }

    public void optimizeGui(ActionEvent e) {

    }
}
