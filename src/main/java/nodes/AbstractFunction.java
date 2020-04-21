package nodes;

import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import tree.TreeContext;

import java.util.Scanner;

/**
 * Represents a Function-like entity. Can be a Function itself or a Native Function.
 */
public abstract class AbstractFunction extends AbstractNode implements IFunctionRenameable, IVariableRenameable {

    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param fileScanner   Scanner pointing to file
     */
    public AbstractFunction(Scanner fileScanner, TreeContext context) {
        super(fileScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public AbstractFunction(TreeContext context) {
        super(context);
    }

    public abstract AbstractNode renameVariable(String oldVariableName, String newVariableName);

    public abstract AbstractNode renameFunction(String oldFunctionName, String newFunctionName);

    /**
     * Returns function name.
     *
     * @return  Function name.
     */
    public abstract String getName();
}
