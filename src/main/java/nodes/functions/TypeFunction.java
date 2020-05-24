package nodes.functions;

import exception.ParsingException;
import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractFunction;
import nodes.AbstractNode;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Native Function. Looks like a function, but begins with "native" rather than "function"
 */
public final class TypeFunction extends AbstractFunction implements IFunctionRenameable, IVariableRenameable {

    private String name;
    private String flags;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public TypeFunction(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public TypeFunction(TreeContext context, String name, String flags) {
        super(context);
        this.name = name;
        this.flags = flags;
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        String line = readLine();
        line = line.replace("\t", " ");
        while(line.contains("  ")) {
            line = line.replace("  ", " ");
        }
        if(line.startsWith("type ")) {
            line = line.substring("type ".length());
        }
        if(line.contains(" ")) {
            name = line.substring(0, line.indexOf(" "));
            line = line.substring(1+name.length());
            flags = line;
        } else {
            name = line;
        }
    }

    /**
     * Renames the variable and all uses of this variable.
     *
     * @param oldVariableName   Existing variable name
     * @param newVariableName   Desired variable name
     * @return                  AST Node with variable declaration/use changed
     */
    @Override
    public final AbstractNode renameVariable(String oldVariableName, String newVariableName) {
        return this;
    }

    /**
     * Renames a function and uses to a new name
     *
     * @param oldFunctionName   Existing function name
     * @param newFunctionName   Desired function name
     * @return                  This node with function/uses renamed
     */
    @Override
    public final AbstractNode renameFunction(String oldFunctionName, String newFunctionName) {
        return new TypeFunction(context, rename(name, oldFunctionName, newFunctionName), flags);
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("type ").append(name);
        if(flags != null && !flags.isEmpty()) {
            builder.append(" ").append(flags);
        }
        return builder.toString();
    }

    /**
     * Converts this node back to its original form.
     *
     * @param indentationLevel Current indentation level
     * @return Original form of this node (code or string) with indentation
     */
    @Override
    public String toFormattedString(int indentationLevel) {
        return this.toString();
    }

    @Override
    public final String getName() {
        return name;
    }

    public final List<Argument> getArguments() {
        List<Argument> arguments = new ArrayList<>();
        return arguments;
    }
}
