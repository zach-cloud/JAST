package interfaces;

import services.RandomNameGeneratorService;
import nodes.j.Script;

import java.io.File;

/**
 * Represents an abstract Syntax Tree root that contains a Script
 */
public interface ISyntaxTree {

    /**
     * Returns the base Script file of the tree.
     *
     * @return  Script file
     */
    Script getScript();

    /**
     * Combines this SyntaxTree with another and then checks
     * for errors. Gracefully handles function main.
     *
     * @param other Other syntax tree to combine
     * @return      Merged syntax tree
     */
    ISyntaxTree merge(ISyntaxTree other);

    /**
     * Changes the name of a variable from old to new name
     *
     * @param oldVariableName   Existing variable name
     * @param newVariableName   New variable name
     * @return                  Tree with variable name changed
     */
    ISyntaxTree renameVariable(String oldVariableName, String newVariableName);

    /**
     * Changes the name of a function from old to new name
     *
     * @param oldFunctionName   Existing function name
     * @param newFunctionName   New function name
     * @return                  Tree with function name changed
     */
    ISyntaxTree renameFunction(String oldFunctionName, String newFunctionName);

    /**
     * Changes the name of a local variable from old to new name
     *
     * @param containingFunction The function that has the local variable inside it
     * @param oldLocalVariableName   Existing local variable name
     * @param newLocalVariableName   New local variable name
     * @return                  Tree with local variable name changed
     */
    ISyntaxTree renameLocalVariable(String containingFunction, String oldLocalVariableName, String newLocalVariableName);

    /**
     * Generates a new Tree with randomized variable and function names
     *
     * @return  New de-duplicated tree
     */
    ISyntaxTree deduplicate(RandomNameGeneratorService generator);

    /**
     * Cleans up the code slightly
     */
    ISyntaxTree postprocess();

    /**
     * Prints out this syntax tree to a file.
     *
     * @param file  File to write to
     */
    void write(File file);
}
