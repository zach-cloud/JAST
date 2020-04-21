package nodes;

import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.functions.Statements;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a statement, i.e. an entity that can appear inside a Function
 */
public abstract class AbstractStatement extends AbstractNode implements IFunctionRenameable, IVariableRenameable {

    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param fileScanner   Scanner pointing to file
     */
    public AbstractStatement(Scanner fileScanner, TreeContext context) {
        super(fileScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public AbstractStatement(TreeContext context) {
        super(context);
    }

    public abstract AbstractNode renameVariable(String oldVariableName, String newVariableName);

    public abstract AbstractNode renameFunction(String oldFunctionName, String newFunctionName);

    /**
     * Converts the given function name into an inline function.
     * Replaces usages of function
     *
     * @param functionName  Function name to replace
     * @param newText       Function text to replace with
     * @return              Replaced statements
     */
    public abstract AbstractStatement inline(String functionName, String newText);

    public abstract boolean usesAsFunction(String functionName);
}
