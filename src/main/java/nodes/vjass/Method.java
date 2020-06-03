package nodes.vjass;

import exception.ParsingException;
import interfaces.IFunctionRenameable;
import interfaces.IMergable;
import interfaces.IVariableRenameable;
import nodes.AbstractFunction;
import nodes.AbstractNode;
import nodes.functions.Argument;
import nodes.functions.FunctionDeclaration;
import nodes.functions.Statements;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Function code block from the function declaration to endfunction
 */
public final class Method extends AbstractFunction implements IMergable, IFunctionRenameable, IVariableRenameable {

    private MethodDeclaration functionDeclaration;
    private Statements statements;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public Method(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    public Method(MethodDeclaration functionDeclaration, Statements statements, TreeContext context) {
        super(context);
        this.functionDeclaration = functionDeclaration;
        this.statements = statements;
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
        return new Method(functionDeclaration,
                (Statements)statements.renameVariable(oldVariableName, newVariableName),
                context);
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
        return new Method((MethodDeclaration) functionDeclaration.renameFunction(oldFunctionName, newFunctionName),
                (Statements)statements.renameFunction(oldFunctionName, newFunctionName),
                context);
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
        builder.append(functionDeclaration.toString()).append("\n");
        builder.append(statements.toString()).append("\n");
        builder.append("endmethod");
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
        StringBuilder builder = new StringBuilder();
        addTabs(builder, indentationLevel-1);
        builder.append(functionDeclaration.toString()).append("\n");
        builder.append(statements.toFormattedString(indentationLevel)).append("\n");
        addTabs(builder, indentationLevel-1);
        builder.append("endmethod");
        return builder.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        // Get first line as function declaration
        this.functionDeclaration = readFunctionDeclaration(readLine());
        // Get lines up to endfunction
        StringBuilder lines = new StringBuilder();
        while(hasNextLine()) {
            String line = readLine();
            if(!line.startsWith("endmethod")) {
                lines.append(line).append("\n");
            }
        }
        if(lines.length() > 0) {
            lines.setLength(lines.length()-1);
        }
        this.statements = readStatements(lines.toString());
    }

    private MethodDeclaration readFunctionDeclaration(String line) {
        MethodDeclaration declaration = new MethodDeclaration(new Scanner(line), context);
        return declaration;
    }

    private Statements readStatements(String lines) {
        Statements statements = new Statements(new Scanner(lines), context);
        return statements;
    }

    public final MethodDeclaration getFunctionDeclaration() {
        return functionDeclaration;
    }

    public final Statements getStatements() {
        return statements;
    }

    @Override
    public String getName() {
        return functionDeclaration.getName();
    }

    /**
     * Combines this AST Node with another and then checks
     * for errors. Gracefully handles function main.
     *
     * @param other Other AST node to combine
     * @return      Merged AST nodes
     */
    @Override
    public final AbstractNode merge(AbstractNode other) {
        Method otherFunction = (Method)other;
        if(!this.functionDeclaration.toString().equals(((Method) other).functionDeclaration.toString())) {
            throw new ParsingException("Incompatible merge: " + this.functionDeclaration.toString() + ", " + ((Method) other).functionDeclaration.toString());
        }

        Statements otherStatements = (Statements)otherFunction.statements;
        Statements newStatements = (Statements)this.statements.merge(otherStatements);

        return new Method(this.functionDeclaration, newStatements, context);
    }

    public boolean usesAsFunction(String functionName) {
        return statements.usesAsFunction(functionName);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Method other = (Method) obj;
        return this.toString().equals(other.toString());
    }

    public final List<Argument> getArguments() {
        List<Argument> arguments = new ArrayList<>();
        arguments.addAll(statements.getArguments());
        return arguments;
    }
}
