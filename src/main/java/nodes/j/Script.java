package nodes.j;

import interfaces.IFunctionRenameable;
import interfaces.IMergable;
import interfaces.IVariableRenameable;
import nodes.AbstractFunction;
import nodes.AbstractNode;
import exception.ParsingException;
import nodes.functions.Argument;
import nodes.functions.Function;
import nodes.functions.Statements;
import nodes.functions.TypeFunction;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a .j file with a globals/functions section.
 */
public final class Script extends AbstractNode implements IMergable, IFunctionRenameable, IVariableRenameable {

    private GlobalsSection globalsSection;
    private FunctionsSection functionsSection;
    private List<TypeFunction> types;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public Script(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    public Script(GlobalsSection globalsSection, FunctionsSection functionsSection, List<TypeFunction> types, TreeContext context) {
        super(context);
        this.globalsSection = globalsSection;
        this.functionsSection = functionsSection;
        this.types = types;
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        return globalsSection.toString() + "\n" + functionsSection.toString();
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
        builder.append(globalsSection.toFormattedString(indentationLevel+1));
        builder.append("\n");
        builder.append(functionsSection.toFormattedString(indentationLevel+1));
        return builder.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        boolean readingGlobals = false; // set to true when "globals" is discovered
        boolean readingFunctions = false; // set to true when "endglobals" is discovered
        StringBuilder currentAccumulatedString = new StringBuilder(); // contains either the globals or endglobal section

        while(hasNextLine()) {
            String line = readLine();
            if(line.equals("globals")) {
                // Read the entire script until endglobals
                if (readingFunctions) {
                    throw new ParsingException("Globals in functions section not supported: " + line);
                }
                if (!readingGlobals) {
                    readingGlobals = true;
                    currentAccumulatedString.append(line).append("\n");
                } else {
                    throw new ParsingException("Nested globals section not supported: " + line);
                }
            } else if(line.startsWith("type")) {
                if(types == null) {
                    types = new ArrayList<>();
                }
                TypeFunction typeFunction = new TypeFunction(new Scanner(line), context);
                types.add(typeFunction);
            } else if(line.equals("endglobals")) {
                // Read the entire script until EOF
                if(readingFunctions) {
                    throw new ParsingException("Globals in functions section not supported: " + line);
                }
                if(readingGlobals) {
                    readingGlobals = false;
                    currentAccumulatedString.append(line);
                    // Parse the globals before resetting
                    this.globalsSection = new GlobalsSection(new Scanner(currentAccumulatedString.toString()), context);
                    currentAccumulatedString = new StringBuilder();
                    readingFunctions = true;
                } else {
                    throw new ParsingException("Found endglobals before globals: " + line);
                }
            } else {
                currentAccumulatedString.append(line).append("\n");
            }
        }
        if(readingFunctions) {
            // Finally parse the Functions
            this.functionsSection = new FunctionsSection(new Scanner(currentAccumulatedString.toString()), context);
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
        GlobalsSection newGlobals = (GlobalsSection)globalsSection.renameVariable(oldVariableName, newVariableName);
        FunctionsSection newFunctions = (FunctionsSection)functionsSection.renameVariable(oldVariableName, newVariableName);
        return new Script(newGlobals, newFunctions, types, context);
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
        GlobalsSection newGlobals = globalsSection;
        FunctionsSection newFunctions = (FunctionsSection)functionsSection.renameFunction(oldFunctionName, newFunctionName);
        return new Script(newGlobals, newFunctions, types, context);
    }

    public final GlobalsSection getGlobalsSection() {
        return globalsSection;
    }

    public final FunctionsSection getFunctionsSection() {
        return functionsSection;
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
        Script otherScript = (Script)other;
        GlobalsSection newGlobals = (GlobalsSection)this.globalsSection.merge(otherScript.globalsSection);
        FunctionsSection newFunctions = (FunctionsSection)this.functionsSection.merge(otherScript.functionsSection);
        return new Script(newGlobals, newFunctions, types, context);
    }

    public final List<Argument> getArguments() {
        List<Argument> baseArguments = new ArrayList<>();
        List<Argument> finalArguments = new ArrayList<>();

        for(Variable variable : globalsSection.getGlobalVariables()) {
            baseArguments.add(variable.getInitialValue());
        }

        for(AbstractFunction function : functionsSection.getFunctions()) {
            baseArguments.addAll(function.getArguments());
        }

        for(Argument argument : baseArguments) {
            if(argument != null) {
                finalArguments.addAll(argument.getArguments());
            }
        }

        return finalArguments;
    }

    public List<TypeFunction> getTypes() {
        return types;
    }
}
