package nodes.functions;

import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import nodes.AbstractStatement;
import exception.ParsingException;
import tree.TreeContext;

import javax.swing.plaf.nimbus.State;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an If Statement which contains conditions and statement blocks
 */
public final class IfStatement extends AbstractStatement implements IFunctionRenameable, IVariableRenameable {

    private Argument condition;
    private Statements thenStatements;
    private Statements elseStatements;
    private List<Argument> elseifConditions;
    private List<Statements> elseifStatements;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public IfStatement(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public IfStatement(Argument condition, Statements thenStatements, Statements elseStatements,
                       List<Argument> elseifConditions, List<Statements> elseifStatements, TreeContext context) {
        super(context);
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
        this.elseifConditions = elseifConditions;
        this.elseifStatements = elseifStatements;
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
        Argument newCondition = null;
        if(condition != null) {
            newCondition = (Argument)condition.renameVariable(oldVariableName, newVariableName);
        }
        Statements newThenStatements = null;
        if(thenStatements != null) {
            newThenStatements = (Statements)thenStatements.renameVariable(oldVariableName, newVariableName);
        }
        Statements newElseStatements = null;
        if(elseStatements != null) {
            newElseStatements = (Statements)elseStatements.renameVariable(oldVariableName, newVariableName);
        }
        List<Argument> newElseifConditions = new ArrayList<>();
        List<Statements> newElseifStatements = new ArrayList<>();
        for(Argument oldArgument : elseifConditions) {
            newElseifConditions.add((Argument)oldArgument.renameVariable(oldVariableName, newVariableName));
        }
        for(Statements oldStatements : elseifStatements) {
            newElseifStatements.add((Statements)oldStatements.renameVariable(oldVariableName, newVariableName));
        }
        return new IfStatement(newCondition, newThenStatements, newElseStatements, newElseifConditions, newElseifStatements, context);
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
        Argument newCondition = null;
        if(condition != null) {
            newCondition = (Argument)condition.renameFunction(oldFunctionName, newFunctionName);
        }
        Statements newThenStatements = null;
        if(thenStatements != null) {
            newThenStatements = (Statements)thenStatements.renameFunction(oldFunctionName, newFunctionName);
        }
        Statements newElseStatements = null;
        if(elseStatements != null) {
            newElseStatements = (Statements)elseStatements.renameFunction(oldFunctionName, newFunctionName);
        }
        List<Argument> newElseifConditions = new ArrayList<>();
        List<Statements> newElseifStatements = new ArrayList<>();
        for(Argument oldArgument : elseifConditions) {
            newElseifConditions.add((Argument)oldArgument.renameFunction(oldFunctionName, newFunctionName));
        }
        for(Statements oldStatements : elseifStatements) {
            newElseifStatements.add((Statements)oldStatements.renameFunction(oldFunctionName, newFunctionName));
        }
        return new IfStatement(newCondition, newThenStatements, newElseStatements, newElseifConditions, newElseifStatements, context);
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
        Argument newCondition = null;
        if(condition != null) {
            newCondition = (Argument)condition.inline(functionName, newText);
        }
        Statements newThenStatements = null;
        if(thenStatements != null) {
            newThenStatements = (Statements)thenStatements.inline(functionName, newText);
        }
        Statements newElseStatements = null;
        if(elseStatements != null) {
            newElseStatements = (Statements)elseStatements.inline(functionName, newText);
        }
        List<Argument> newElseifConditions = new ArrayList<>();
        List<Statements> newElseifStatements = new ArrayList<>();
        for(Argument oldArgument : elseifConditions) {
            newElseifConditions.add((Argument)oldArgument.inline(functionName, newText));
        }
        for(Statements oldStatements : elseifStatements) {
            newElseifStatements.add((Statements)oldStatements.inline(functionName, newText));
        }
        return new IfStatement(newCondition, newThenStatements, newElseStatements, newElseifConditions, newElseifStatements, context);
    }

    public boolean usesAsFunction(String functionName) {
        if(condition != null && condition.usesAsFunction(functionName)) {
            return true;
        }
        if(thenStatements != null && thenStatements.usesAsFunction(functionName)) {
            return true;
        }
        if(elseStatements != null && elseStatements.usesAsFunction(functionName)) {
            return true;
        }
        for(Argument oldArgument : elseifConditions) {
            if(oldArgument != null && oldArgument.usesAsFunction(functionName)) {
                return true;
            }
        }
        for(Statements oldStatements : elseifStatements) {
            if(oldStatements != null && oldStatements.usesAsFunction(functionName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected final void setupVariables() {
        elseifConditions = new ArrayList<>();
        elseifStatements = new ArrayList<>();
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
        built.append("if ").append(condition.toString()).append(" then").append("\n");
        built.append(thenStatements.toString()).append("\n");
        for(int i = 0; i < elseifConditions.size(); i++) {
            // Elseif order is maintained by List
            built.append("elseif ").append(elseifConditions.get(i).toString()).append(" then").append("\n");
            built.append(elseifStatements.get(i).toString()).append("\n");
        }
        // Else comes last always
        if(elseStatements != null && !elseStatements.getStatements().isEmpty()) {
            built.append("else").append("\n");
            built.append(elseStatements.toString()).append("\n");
        }
        built.append("endif");
        return built.toString();
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
        built.append("if ").append(condition.toString()).append(" then").append("\n");
        built.append(thenStatements.toFormattedString(indentationLevel+1)).append("\n");
        for(int i = 0; i < elseifConditions.size(); i++) {
            // Elseif order is maintained by List
            addTabs(built, indentationLevel);
            built.append("elseif ").append(elseifConditions.get(i).toString()).append(" then").append("\n");
            built.append(elseifStatements.get(i).toFormattedString(indentationLevel+1)).append("\n");
        }
        // Else comes last always
        if(elseStatements != null) {
            addTabs(built, indentationLevel);
            built.append("else").append("\n");
            built.append(elseStatements.toFormattedString(indentationLevel+1)).append("\n");
        }
        addTabs(built, indentationLevel);
        built.append("endif");
        return built.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        StringBuilder currentCondition = new StringBuilder(); // Contains the currently-read condition
        StringBuilder currentStatements = new StringBuilder(); // Contains the currently-read statements block
        List<String> assembledElseifConditions = new ArrayList<>(); // List of all discovered elseif statement conditions
        List<String> assembledElseifStatements = new ArrayList<>(); // List of all discovered elseif statement blocks
        int currentIfLevel = 0; // Maintain if level because there can be nested ifs
        boolean readingThenStatement = false; // Set to true when If block Statements is being read
        boolean readingElseStatement = false; // Set to true when Else block Statements is being read
        boolean readingElseifStatement = false; // Set to true when Elseif block Statements is being read

        while(hasNextLine()) {
            String line = readLine();
            if(line.endsWith("then")) {
                line = line.substring(0, line.indexOf("then"));
                line = line + " then";
            }
            if(line.startsWith("if")) {
                currentIfLevel++;
                if(!readingThenStatement && !readingElseStatement && !readingElseifStatement) {
                    // Found first if statement
                    readingThenStatement = true;
                    currentCondition.append(line);
                } else {
                    currentStatements.append(line).append("\n");
                }
            } else if(line.startsWith("endif") && currentIfLevel > 0) {
                currentIfLevel--;
                if(currentIfLevel == 0) {
                    // This ended top-level condition, not nested condition
                    // Find which condition was ended
                    if(readingThenStatement) {
                        saveThenStatement(currentCondition, currentStatements);
                    } else if(readingElseStatement) {
                        saveElseStatement(currentStatements);
                    } else if(readingElseifStatement) {
                        saveElseifStatement(currentCondition, currentStatements, assembledElseifConditions, assembledElseifStatements);
                    } else {
                        throw new ParsingException("Inconsistent state: " + line);
                    }
                } else {
                    currentStatements.append(line).append("\n");
                }
            } else if(line.startsWith("else") && !line.startsWith("elseif") && currentIfLevel == 1) {
                // Done with then/elseif statement, enter else statement
                if(readingThenStatement && !readingElseStatement && !readingElseifStatement) {
                    readingThenStatement = false;
                    readingElseStatement = true;
                    saveThenStatement(currentCondition, currentStatements);
                } else if(readingElseStatement && !readingThenStatement && !readingElseifStatement) {
                    readingThenStatement = true;
                    readingElseStatement = false;
                    saveElseStatement(currentStatements);
                } else if(readingElseifStatement && !readingThenStatement && !readingElseStatement) {
                    saveElseifStatement(currentCondition, currentStatements, assembledElseifConditions, assembledElseifStatements);
                    readingElseifStatement = false;
                    readingElseStatement = true;
                    currentCondition.append(line);
                } else {
                    throw new ParsingException("Inconsistent state: " + line);
                }
            } else if(line.startsWith("elseif") && currentIfLevel == 1) {
                if(readingThenStatement) {
                    // We went from then statement to elseif statement
                    readingThenStatement = false;
                    readingElseifStatement = true;
                    saveThenStatement(currentCondition, currentStatements);
                    currentCondition.append(line);
                } else if(readingElseifStatement) {
                    // We went from elseif to elseif
                    saveElseifStatement(currentCondition, currentStatements, assembledElseifConditions, assembledElseifStatements);
                    currentCondition.append(line);
                } else {
                    throw new ParsingException("Malformed syntax: " + line);
                }
            } else if(currentIfLevel > 0) {
                currentStatements.append(line).append("\n");
            }
        }
        if(elseifConditions.size() != elseifStatements.size()) {
            // Code got confused somehow. Throw exception here rather than later.
            // Hopefully this never happens.
            throw new ParsingException("Inconsistent internal state. Elseif conditions did not match elseif statements. Submit bug report.");
        }
        if(!assembledElseifConditions.isEmpty() && !assembledElseifStatements.isEmpty()) {
            saveElseifFromLists(assembledElseifConditions, assembledElseifStatements);
        }
    }

    /**
     * Saves the given statement as an else statement block
     *
     * @param currentStatements String builder with statement lines
     */
    private final void saveElseStatement(StringBuilder currentStatements) {
        removeFinalCharacter(currentStatements);
        this.elseStatements = new Statements(new Scanner(currentStatements.toString()), context);
        clearStringBuilder(currentStatements);
    }

    /**
     * Saves the given statement/condition pair as an elseif statement block
     *
     * @param currentCondition  The elseif condition
     * @param currentStatements The elseif statements section
     * @param assembledElseifConditions Existing array of elseif conditions
     * @param assembledElseifStatements Existing array of elseif statements section
     */
    private final void saveElseifStatement(StringBuilder currentCondition, StringBuilder currentStatements, List<String> assembledElseifConditions, List<String> assembledElseifStatements) {
        removeFinalCharacter(currentStatements);
        assembledElseifConditions.add(trimCondition(currentCondition.toString()));
        assembledElseifStatements.add(currentStatements.toString());
        clearStringBuilder(currentCondition);
        clearStringBuilder(currentStatements);
    }

    /**
     * Saves then condition and statements
     *
     * @param currentCondition  Then condition
     * @param currentStatements Then statements block
     */
    private final void saveThenStatement(StringBuilder currentCondition, StringBuilder currentStatements) {
        removeFinalCharacter(currentStatements);
        this.condition = new Argument(new Scanner(trimCondition(currentCondition.toString())), context);
        this.thenStatements = new Statements(new Scanner(currentStatements.toString()), context);
        clearStringBuilder(currentCondition);
        clearStringBuilder(currentStatements);
    }

    /**
     * Converts from a String array into the elseif block models
     *
     * @param assembledElseifConditions Elseif conditions strings
     * @param assembledElseifStatements Elseif statements strings
     */
    private void saveElseifFromLists(List<String> assembledElseifConditions, List<String> assembledElseifStatements) {
        for(String condition : assembledElseifConditions) {
            Argument newCondition = new Argument(new Scanner(condition), context);
            this.elseifConditions.add(newCondition);
        }
        for(String condition : assembledElseifStatements) {
            Statements newStatements = new Statements(new Scanner(condition), context);
            this.elseifStatements.add(newStatements);
        }
    }

    /**
     * Resets the StringBuilder
     *
     * @param builder   Builder to reset
     */
    private void clearStringBuilder(StringBuilder builder) {
        builder.setLength(0);
    }

    /**
     * Trims unnecessary words from condition (if, elseif, then, etc).
     *
     * @param conditionString   Condition to trim
     * @return                  Trimmed condition
     */
    private String trimCondition(String conditionString) {
        if(conditionString.startsWith("if ")) {
            conditionString = conditionString.substring("if ".length());
        } else if(conditionString.startsWith("elseif ")) {
            conditionString = conditionString.substring("elseif ".length());
        }
        if(conditionString.endsWith(" then")) {
            conditionString = conditionString.substring(0, conditionString.length()-5);
        }
        return conditionString.trim();
    }

    public final Argument getCondition() {
        return condition;
    }

    public final Statements getThenStatements() {
        return thenStatements;
    }

    public final Statements getElseStatements() {
        return elseStatements;
    }

    public final List<Argument> getElseifConditions() {
        return Collections.unmodifiableList(elseifConditions);
    }

    public final List<Statements> getElseifStatements() {
        return Collections.unmodifiableList(elseifStatements);
    }

    public final List<Argument> getArguments() {
        List<Argument> arguments = new ArrayList<>();
        if(condition != null) {
            arguments.add(condition);
        }
        if(thenStatements != null) {
            arguments.addAll(thenStatements.getArguments());
        }
        if(elseStatements != null) {
            arguments.addAll(elseStatements.getArguments());
        }
        for(Argument oldArgument : elseifConditions) {
            arguments.add(oldArgument);
        }
        for(Statements oldStatements : elseifStatements) {
            arguments.addAll(oldStatements.getArguments());
        }
        return arguments;
    }
}
