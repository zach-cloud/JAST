package services;

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
            String userPath = System.getProperty("user.dir") + "/";
            Runtime rt = Runtime.getRuntime();
            String command = "\"" + userPath + "jasshelper/clijasshelper.exe\" --scriptonly \"" + userPath + "jasshelper/common.j\" \"" + userPath + "jasshelper/blizzard.j\" \"" + userPath + "jasshelper/tmp.j\" " + userPath + "jasshelper/tmp2.j\"";
            System.out.println("Sending command: " + command);
            Process process = rt.exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            StringBuilder result = new StringBuilder();

            addStreamToStringBuilder(stdInput, result);
            addStreamToStringBuilder(stdError, result);

            return result.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    private void addStreamToStringBuilder(BufferedReader stream, StringBuilder result) throws IOException {
        String s = null;
        while ((s = stream.readLine()) != null) {
            result.append(s).append("\n");
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
