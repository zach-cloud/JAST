package services;

import interfaces.IFileWriterService;
import nodes.AbstractFunction;
import nodes.AbstractStatement;
import nodes.functions.Function;
import nodes.functions.LocalStatement;
import nodes.functions.ReturnStatement;
import nodes.functions.Statements;
import nodes.j.FunctionsSection;
import nodes.j.GlobalsSection;
import nodes.j.Script;
import nodes.j.Variable;
import exception.SyntaxErrorException;
import interfaces.ISyntaxChecker;
import interfaces.ISyntaxTree;
import tree.SyntaxTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

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
        // Check that pjass exists
        checkForPjass();
        // Make temp file containing code
        deleteTempFile();
        fileWriterService.write(tree, "PJASS/tmp.j");
        String result = executePjassCommands();
        deleteTempFile();
        return result;
    }

    private String executePjassCommands() {
        try {
            String userPath = System.getProperty("user.dir") + "/";
            Runtime rt = Runtime.getRuntime();
            String command = userPath + "PJASS/pjass.exe " + userPath + "PJASS/common.j " + userPath + "PJASS/blizzard.j " + userPath + "PJASS/common.ai " + userPath + "PJASS/tmp.j ";
            Process proc = rt.exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

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
        File tempFile = new File("PJASS/tmp.j");
        if(tempFile.exists()) {
            tempFile.delete();
        }
    }

    private void checkForPjass() {
        String[] expectedFiles = {"PJASS/pjass.exe",
                "PJASS/blizzard.j", "PJASS/common.j",
                "PJASS/common.ai"};
        for(String expectedFileName : expectedFiles) {
            File expectedFile = new File(expectedFileName);
            if(!expectedFile.exists()) {
                throw new IllegalStateException("File does not exist: " + expectedFile.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {
        SyntaxCheckerService syntaxCheckerService = new SyntaxCheckerService();
        ISyntaxTree tree = SyntaxTree.readTree(new File("badSyntax1"));
        System.out.println(syntaxCheckerService.syntaxCheck(tree));
    }
}
