package nodes.functions;

import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import nodes.AbstractStatement;
import exception.ParsingException;
import tree.TreeContext;

import java.util.Scanner;

/**
 * Represents a block of code starting with loop and going until endloop
 */
public final class LoopStatement extends AbstractStatement implements IFunctionRenameable, IVariableRenameable {

    private Statements statements;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public LoopStatement(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public LoopStatement(Statements statements, TreeContext context) {
        super(context);
        this.statements = statements;
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        StringBuilder built = new StringBuilder();
        built.append("loop").append("\n");
        built.append(statements.toString()).append("\n");
        built.append("endloop");
        return built.toString();
    }

    public final AbstractStatement renameVariable(String oldVariableName, String newVariableName) {
        return new LoopStatement((Statements)statements.renameVariable(oldVariableName, newVariableName), context);
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
        return new LoopStatement((Statements)statements.inline(functionName, newText), context);
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
        return new LoopStatement((Statements)statements.renameFunction(oldFunctionName, newFunctionName), context);
    }

    public boolean usesAsFunction(String functionName) {
        return statements.usesAsFunction(functionName);
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        StringBuilder loopLines = new StringBuilder();
        boolean readingLoop = false;
        int loopLevel = 0; // There can be nested loops!
        while(hasNextLine()) {
            String line = readLine();
            if(line.startsWith("loop")) {
                loopLevel++;
                if(loopLevel == 1) {
                    readingLoop = true;
                } else {
                    loopLines.append(line).append("\n"); // It's a nested loop
                }

            } else if(line.startsWith("endloop")) {
                if(loopLevel == 1) {
                    if (readingLoop) {
                        readingLoop = false;
                    } else {
                        throw new ParsingException("Found endloop before loop");
                    }
                } else {
                    // If not, then it's an endloop for a nested loop
                    loopLines.append(line).append("\n");
                    loopLevel--;
                }
            } else if(readingLoop) {
                loopLines.append(line).append("\n");
            }
        }
        removeFinalCharacter(loopLines);
        statements = new Statements(new Scanner(loopLines.toString()), context);
    }

    public final Argument getExitCondition() {
        for(AbstractStatement statement: statements.getStatements()) {
            if(statement instanceof ExitWhenStatement) {
                return ((ExitWhenStatement)statement).getExitwhenCondition();
            }
        }
        return null;
    }

    public final Statements getStatements() {
        return statements;
    }
}
