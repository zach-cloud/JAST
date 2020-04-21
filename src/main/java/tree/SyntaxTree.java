package tree;

import exception.MergeFailureException;
import exception.ParsingException;
import exception.RenameFailureException;
import exception.WritingException;
import interfaces.IPreprocessFileService;
import services.RandomNameGeneratorService;
import nodes.AbstractFunction;
import nodes.j.Script;
import services.PreprocessFileService;
import services.SyntaxCheckerService;
import interfaces.ISyntaxChecker;
import interfaces.ISyntaxTree;
import nodes.j.Variable;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a syntactically-correct and correctly-formatted
 * JASS script
 */
public final class SyntaxTree implements ISyntaxTree {

    private Script script;

    /**
     * Creates a new SyntaxTree from a pre-existing Script file.
     *
     * @param script    Script file to create from
     */
    public SyntaxTree(Script script) {
        this.script = script;
        ISyntaxChecker checker = new SyntaxCheckerService();
        checker.syntaxCheck(this);
    }

    /**
     * Reads a SyntaxTree from a full, provides script String
     *
     * @param input Input Script
     * @return      Read syntax tree
     */
    public static ISyntaxTree readTree(String input) {
        TreeContext context = new TreeContext();
        IPreprocessFileService preprocessor = new PreprocessFileService();
        try {
            Script script = new Script(preprocessor.preprocessFile(new Scanner(input)), context);
            ISyntaxTree tree = new SyntaxTree(script);
            ISyntaxChecker checker = new SyntaxCheckerService();
            checker.syntaxCheck(tree);
            return tree;
        } catch (Exception ex) {
            throw new ParsingException("Failed to parse tree: " + ex.getMessage() + ". Last line: " + context.getLastLine());
        }
    }

    /**
     * Reads a SyntaxTree from a full, provides script File
     *
     * @param inputFile Input Script (file)
     * @return          Read syntax tree
     */
    public static ISyntaxTree readTree(File inputFile) {
        try {
            return readTree(FileUtils.readFileToString(inputFile, Charset.defaultCharset()));
        } catch (Exception ex) {
            throw new ParsingException(ex);
        }
    }

    /**
     * Writes this SyntaxTree out to a file.
     *
     * @param file  File path to write to
     */
    @Override
    public final void write(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(script.toString());
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            throw new WritingException(ex);
        }
    }

    /**
     * Combines this SyntaxTree with another and then checks
     * for errors. Gracefully handles function main.
     *
     * @param other Other syntax tree to combine
     * @return      Merged syntax tree
     */
    @Override
    public final ISyntaxTree merge(ISyntaxTree other) {
        try {
            Script newScript = (Script) this.script.merge(other.getScript());
            return new SyntaxTree(newScript);
        } catch (Exception ex) {
            throw new MergeFailureException(ex);
        }
    }

    /**
     * Changes the name of a variable from old to new name
     *
     * @param oldVariableName   Existing variable name
     * @param newVariableName   New variable name
     * @return                  Script with variable name changed
     */
    @Override
    public final ISyntaxTree renameVariable(String oldVariableName, String newVariableName) {
        try {
            Script newScript = (Script)this.script.renameVariable(oldVariableName, newVariableName);
            return new SyntaxTree(newScript);
        } catch (Exception ex) {
            throw new RenameFailureException(ex);
        }
    }

    /**
     * Changes the name of a function from old to new name
     *
     * @param oldFunctionName Existing function name
     * @param newFunctionName New function name
     * @return Tree with function name changed
     */
    @Override
    public final ISyntaxTree renameFunction(String oldFunctionName, String newFunctionName) {
        try {
            Script newScript = (Script)this.script.renameFunction(oldFunctionName, newFunctionName);
            return new SyntaxTree(newScript);
        } catch (Exception ex) {
            throw new RenameFailureException(ex);
        }
    }

    /**
     * Cleans up the code slightly
     */
    @Override
    public ISyntaxTree postprocess() {
        String script = this.toString();
        while(script.contains("\n\n")) {
            script = script.replace("\n\n", "\n");
        }
        return SyntaxTree.readTree(script);
    }

    /**
     * Changes the name of a local variable from old to new name
     *
     * @param containingFunction   The function that has the local variable inside it
     * @param oldLocalVariableName Existing local variable name
     * @param newLocalVariableName New local variable name
     * @return Tree with local variable name changed
     */
    @Override
    public final ISyntaxTree renameLocalVariable(String containingFunction, String oldLocalVariableName, String newLocalVariableName) {
        return null;
    }

    /**
     * Generates a new Tree with randomized variable and function names
     *
     * @return  New de-duplicated tree
     */
    @Override
    public final ISyntaxTree deduplicate(RandomNameGeneratorService generator) {
        ISyntaxTree newTree = this;
        List<Variable> variables = script.getGlobalsSection().getGlobalVariables();
        List<AbstractFunction> functions = script.getFunctionsSection().getFunctions();
        for(Variable var : variables) {
            newTree = newTree.renameVariable(var.getName(), generator.next());
        }
        for(AbstractFunction function : functions) {
            if(!function.getName().equals("main")) {
                newTree = newTree.renameFunction(function.getName(), generator.next());
            }
        }
        return newTree;
    }

    /**
     * Provides the Script object that this SyntaxTree contains.
     *
     * @return  Script object
     */
    @Override
    public final Script getScript() {
        return script;
    }

    /**
     * Returns the JASS Script file as a String
     *
     * @return  JASS Script as String
     */
    @Override
    public final String toString() {
        return script.toString();
    }
}
