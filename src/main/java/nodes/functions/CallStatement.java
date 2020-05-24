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
 * Represents a simple call condition, like:
 * call myFunction(a,b,c)
 */
public final class CallStatement extends AbstractStatement implements IFunctionRenameable, IVariableRenameable {

    private String functionName;
    private Argument callArgument;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public CallStatement(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public CallStatement(String functionName, Argument callArgument, TreeContext context) {
        super(context);
        this.functionName = functionName;
        this.callArgument = callArgument;
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
        return new CallStatement(functionName, (Argument)callArgument.renameVariable(oldVariableName, newVariableName), context);
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
        return new CallStatement(functionName, (Argument)callArgument.renameFunction(oldFunctionName, newFunctionName), context);
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
        return new CallStatement(functionName, (Argument)callArgument.inline(functionName, newText), context);
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        String functionName;
        String arguments;
        String line = readLine();
        if(!line.startsWith("call ")) {
            throw new ParsingException("Not a call statement: " + line);
        }
        String statementText = line.substring(5);
        if(!statementText.contains("(")) {
            throw new ParsingException("Call statement without named function: " + line);
        }
        functionName = statementText.substring(0, statementText.indexOf("("));
        arguments = trimParenthesis(statementText.substring(statementText.indexOf("(")));
        this.functionName = functionName + "(" + arguments + ")";
        this.callArgument = new Argument(new Scanner(statementText), context);
    }

    public boolean usesAsFunction(String functionName) {
        return callArgument.usesAsFunction(functionName);
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        return "call " + callArgument.toString();
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

    public final String getFunctionName() {
        return functionName;
    }

    public final List<Argument> getArguments() {
        List<Argument> arguments = new ArrayList<>();
        arguments.addAll(callArgument.getArguments());
        return arguments;
    }
}
