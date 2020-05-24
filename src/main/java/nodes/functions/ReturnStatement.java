package nodes.functions;

import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import nodes.AbstractStatement;
import exception.ParsingException;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a return statement, either returning something or nothing
 */
public final class ReturnStatement extends AbstractStatement implements IFunctionRenameable, IVariableRenameable {

    private Argument returnArgument;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public ReturnStatement(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public ReturnStatement(Argument returnArgument, TreeContext context) {
        super(context);
        this.returnArgument = returnArgument;
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
        return new ReturnStatement((Argument)returnArgument.renameVariable(oldVariableName, newVariableName), context);
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
        return new ReturnStatement((Argument)(returnArgument.renameFunction(oldFunctionName, newFunctionName)), context);
    }

    /**
     * Converts the given function name into an inline function.
     * Replaces usages of function
     *
     * @param functionName Function name to replace
     * @param newText      Function text to replace with
     * @return Replaced statements
     */
    @Override
    public AbstractStatement inline(String functionName, String newText) {
        return new ReturnStatement((Argument)(returnArgument.inline(functionName, newText)), context);
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        StringBuilder returnStatement = new StringBuilder();
        returnStatement.append("return");
        if(returnArgument != null && !getReturnBody().isEmpty()) {
            returnStatement.append(" ").append(getReturnBody());
        }
        return returnStatement.toString();
    }

    /**
     * Converts this node back to its original form.
     *
     * @param indentationLevel Current indentation level
     * @return Original form of this node (code or string) with indentation
     */
    @Override
    public String toFormattedString(int indentationLevel) {
        StringBuilder built = new StringBuilder();
        addTabs(built, indentationLevel);
        built.append(this.toString());
        return built.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        String line = readLine();
        if(!line.startsWith("return")) {
            throw new ParsingException("Not a return statement: " + line);
        }
        line = line.substring("return".length()).trim();
        this.returnArgument = new Argument(new Scanner(line), context);
    }

    public boolean usesAsFunction(String functionName) {
        return returnArgument.usesAsFunction(functionName);
    }

    public final String getReturnBody() {
        return returnArgument.toString();
    }

    public final List<Argument> getArguments() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(returnArgument);
        return arguments;
    }
}
