package services;

import constants.Constants;
import helper.InterfaceHelper;
import interfaces.IFileWriterService;
import exception.SyntaxErrorException;
import interfaces.ISyntaxChecker;
import interfaces.ISyntaxTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class to check some basic syntax errors
 */
public class SyntaxCheckerService implements ISyntaxChecker {

    private IFileWriterService fileWriterService;

    public SyntaxCheckerService() {
        this.fileWriterService = new FileWriterService();
    }

    public SyntaxCheckerService(IFileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }

    /**
     * Checks the tree for valid syntax.
     *
     * @param tree  Syntax tree to check
     */
    @Override
    public String syntaxCheck(ISyntaxTree tree) throws SyntaxErrorException {
        // Check that jasshelper exists
        checkForFiles();
        // Make temp file containing code
        deleteTempFile();
        if(tree.getScript().getFunctionsSection() == null ||
           !tree.getScript().getFunctionsSection().toString().contains("function main takes nothing returns nothing")) {
            tree.addFunctionMain();
        }
        fileWriterService.write(tree, "jasshelper/tmp.j");
        String result = executeJasshelper();
        deleteTempFile();
        return result;
    }

    private String executeJasshelper() {
        try {
            String command = "\"" + Constants.USER_PATH + "jasshelper/clijasshelper.exe\" --scriptonly \"" + Constants.USER_PATH + "jasshelper/common.j\" \"" + Constants.USER_PATH + "jasshelper/blizzard.j\" \"" + Constants.USER_PATH + "jasshelper/tmp.j\" " + Constants.USER_PATH + "jasshelper/tmp2.j\"";
            return InterfaceHelper.executeCommand(command);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }



    private void deleteTempFile() {
        File tempFile = new File("jasshelper/tmp.j");
        if(tempFile.exists()) {
            //tempFile.delete();
        }
    }

    private void checkForFiles() {
        String[] expectedFiles = {"jasshelper/clijasshelper.exe",
                "jasshelper/blizzard.j", "jasshelper/common.j"};
        for(String expectedFileName : expectedFiles) {
            File expectedFile = new File(expectedFileName);
            if(!expectedFile.exists()) {
                throw new IllegalStateException("File does not exist: " + expectedFile.getAbsolutePath());
            }
        }
    }
}
