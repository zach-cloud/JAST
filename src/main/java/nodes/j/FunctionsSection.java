package nodes.j;

import interfaces.IFunctionRenameable;
import interfaces.IMergable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import nodes.AbstractFunction;
import nodes.functions.Function;
import nodes.functions.NativeFunction;
import exception.ParsingException;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a code fragment from endglobals (not included) to the EOF
 */
public final class FunctionsSection extends AbstractNode implements IMergable, IFunctionRenameable, IVariableRenameable {

    private List<AbstractFunction> functions;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public FunctionsSection(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    public FunctionsSection(List<AbstractFunction> functions, TreeContext context) {
        super(context);
        this.functions = new ArrayList<>();
        this.functions.addAll(functions);
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
        List<AbstractFunction> functions = new ArrayList<>();
        for(AbstractFunction function : this.functions) {
            functions.add((AbstractFunction)function.renameVariable(oldVariableName, newVariableName));
        }
        return new FunctionsSection(functions, context);
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
        List<AbstractFunction> functions = new ArrayList<>();
        for(AbstractFunction function : this.functions) {
            functions.add((Function)function.renameFunction(oldFunctionName, newFunctionName));
        }
        return new FunctionsSection(functions, context);
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected void setupVariables() {
        this.functions = new ArrayList<>();
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
        for(AbstractFunction function: functions) {
            builder.append(function.toString()).append("\n");
        }
        removeFinalCharacter(builder);
        return builder.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        StringBuilder currentFunction = new StringBuilder();
        boolean readingFunction = false; // Set to true when function is discovered, back to false at endfunctions

        while(hasNextLine()) {
            String line = readLine();
            if(line.startsWith("function ") || line.startsWith("constant function ")) {
                if(!readingFunction) {
                    currentFunction.append(line).append("\n");
                    readingFunction = true;
                } else {
                    throw new ParsingException("Found function without endfunction: " + line);
                }
            } else if(line.startsWith("endfunction")) {
                if(!readingFunction) {
                    throw new ParsingException("Found endfunction without function: " + line);
                } else {
                    currentFunction.append(line);
                    Function function = new Function(new Scanner(currentFunction.toString()), context);
                    this.functions.add(function);
                    currentFunction = new StringBuilder();
                    readingFunction = false;
                }
            } else if(readingFunction) {
                currentFunction.append(line).append("\n");
            } else if(line.startsWith("native ") || line.startsWith("constant native ")) {
                NativeFunction nativeFunction = new NativeFunction(new Scanner(line), context);
                functions.add(nativeFunction);
            } else {
                // Not a Function or Native
                if(!line.isEmpty()) {
                    throw new ParsingException("Unrecognized line in functions section: " + line);
                }
            }
        }
    }

    public final List<AbstractFunction> getFunctions() {
        return Collections.unmodifiableList(functions);
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
        Function mainFunction = null;

        List<AbstractFunction> newFunctions = new ArrayList<>();
        FunctionsSection otherFunctions = (FunctionsSection)other;
        for(AbstractFunction function : functions) {
            if(function instanceof NativeFunction) {
                newFunctions.add(function);
            }
        }
        for(AbstractFunction function : otherFunctions.functions) {
            if(function instanceof NativeFunction) {
                newFunctions.add(function);
            }
        }
        for(AbstractFunction function : functions) {
            if(function instanceof Function) {
                if(function.getName().equals("main")) {
                    mainFunction = (Function)function;
                } else {
                    newFunctions.add(function);
                }
            }
        }

        for(AbstractFunction function : otherFunctions.functions) {
            if(function instanceof Function) {
                if(function.getName().equals("main") && mainFunction != null) {
                    mainFunction = (Function)mainFunction.merge(function);
                } else {
                    newFunctions.add(function);
                }
            }
        }
        if(mainFunction != null) {
            newFunctions.add(mainFunction);
        }
        return new FunctionsSection(newFunctions, context);
    }
}
