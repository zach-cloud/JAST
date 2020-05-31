package nodes.vjass;

import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import nodes.functions.GenericDeclaration;
import nodes.functions.Inputs;
import nodes.functions.Output;
import tree.TreeContext;

import java.util.Scanner;

/**
 * Function Declaration line (i.e. function x takes y returns z)
 */
public final class MethodDeclaration extends GenericDeclaration implements IFunctionRenameable, IVariableRenameable {

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public MethodDeclaration(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
        setStartText("method");
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public MethodDeclaration(TreeContext context, String name, Inputs inputs, Output output, boolean constant, String accessModifier) {
        super(context, name, inputs, output, constant, accessModifier);
        setStartText("method");
    }

    @Override
    protected void setupVariables() {
        super.setupVariables();
        setStartText("method");
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
        return new MethodDeclaration(context, rename(getName(), oldFunctionName, newFunctionName), getInputs(), getOutput(), isConstant(), getAccessModifier());
    }
}
