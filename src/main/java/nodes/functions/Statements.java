package nodes.functions;

import interfaces.IFunctionRenameable;
import interfaces.IMergable;
import interfaces.IVariableRenameable;
import nodes.AbstractFunction;
import nodes.AbstractNode;
import nodes.AbstractStatement;
import exception.ParsingException;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a block of one or more generic statements
 */
public final class Statements extends AbstractNode implements IMergable, IFunctionRenameable, IVariableRenameable {

    private List<AbstractStatement> statements;

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected final void setupVariables() {
        this.statements = new ArrayList<>();
    }

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public Statements(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    public Statements(List<AbstractStatement> statements, TreeContext context) {
        super(context);
        this.statements = new ArrayList<>();
        this.statements.addAll(statements);
    }

    /**
     * Renames the variable and all uses of this variable.
     *
     * @param oldVariableName Existing variable name
     * @param newVariableName Desired variable name
     * @return AST Node with variable declaration/use changed
     */
    @Override
    public final AbstractNode renameVariable(String oldVariableName, String newVariableName) {
        List<AbstractStatement> newStatements = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            newStatements.add((AbstractStatement) statement.renameVariable(oldVariableName, newVariableName));
        }
        return new Statements(newStatements, context);
    }

    /**
     * Converts the given function name into an inline function.
     * Replaces usages of function
     *
     * @param functionName Function name to replace
     * @param newText      Function text to replace with
     * @return Replaced statements
     */
    public final Statements inline(String functionName, String newText) {
        List<AbstractStatement> newStatements = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            newStatements.add((AbstractStatement) statement.inline(functionName, newText));
        }
        return new Statements(newStatements, context);
    }

    /**
     * Renames a function and uses to a new name
     *
     * @param oldFunctionName Existing function name
     * @param newFunctionName Desired function name
     * @return This node with function/uses renamed
     */
    @Override
    public final AbstractNode renameFunction(String oldFunctionName, String newFunctionName) {
        List<AbstractStatement> newStatements = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            newStatements.add((AbstractStatement) statement.renameFunction(oldFunctionName, newFunctionName));
        }
        return new Statements(newStatements, context);
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
        for (AbstractStatement statement : statements) {
            built.append(statement.toString()).append("\n");
        }
        if (built.length() > 0) {
            built.setLength(built.length() - 1);
        }
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
        for (AbstractStatement statement : statements) {
            built.append(statement.toFormattedString(indentationLevel)).append("\n");
        }
        if (built.length() > 0) {
            built.setLength(built.length() - 1);
        }
        return built.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        boolean readingLocals = true; // set to false when any line other than locals is discovered

        while (hasNextLine()) {
            String line = readLine();
            // Determine what type of method to read and then read it
            if (line.startsWith("call ")) {
                // Call statements are a single line
                CallStatement statement = new CallStatement(new Scanner(line), context);
                statements.add(statement);
                readingLocals = false;
            } else if (line.startsWith("set ")) {
                // Set statements are a single line
                SetStatement statement = new SetStatement(new Scanner(line), context);
                statements.add(statement);
                readingLocals = false;
            } else if (line.startsWith("local ")) {
                // Local statements are a single line AND must come at the start of the file
                if (readingLocals) {
                    LocalStatement statement = new LocalStatement(new Scanner(line), context);
                    statements.add(statement);
                } else {
                    throw new ParsingException("Locals section out of place: " + line);
                }
            } else if (line.startsWith("if ")) {
                // If statements contain inner blocks of code
                readIfStatement(line);
                readingLocals = false;
            } else if (line.startsWith("loop")) {
                // Loop statements contain inner blocks of code
                readLoopStatememt(line);
                readingLocals = false;
            } else if (line.startsWith("exitwhen ")) {
                // Exitwhen statements exist on their own but will only be found inside loops
                ExitWhenStatement statement = new ExitWhenStatement(new Scanner(line), context);
                statements.add(statement);
                readingLocals = false;
            } else if (line.startsWith("return")) {
                // There can be multiple returns in a single function.
                ReturnStatement statement = new ReturnStatement(new Scanner(line), context);
                statements.add(statement);
                readingLocals = false;
            } else {
                if (!line.isEmpty()) {
                    throw new ParsingException("Unrecognized line in statement: " + line);
                }
            }
        }
    }

    /**
     * Reads from if -> endif and parses it as an IfStatement
     *
     * @param firstLine First line that triggered this if reading
     */
    private final void readIfStatement(String firstLine) {
        StringBuilder fullStatement = new StringBuilder();
        fullStatement.append(firstLine).append("\n");
        String newLine = "";
        int ifLevel = 0;
        boolean exit = false;
        while (!exit) {
            newLine = readLine();
            fullStatement.append(newLine).append("\n");
            if (newLine.startsWith("if ")) {
                ifLevel++;
            } else if (newLine.startsWith("endif")) {
                if (ifLevel == 0) {
                    exit = true;
                }
                ifLevel--;
            }
        }
        IfStatement ifStatement = new IfStatement(new Scanner(fullStatement.toString()), context);
        statements.add(ifStatement);
    }

    /**
     * Reads from loop -> endloop and parses it as an LoopStatement
     *
     * @param firstLine First line that triggered this loop reading
     */
    private final void readLoopStatememt(String firstLine) {
        StringBuilder fullStatement = new StringBuilder();
        fullStatement.append(firstLine).append("\n");
        String newLine = "";
        int loopLevel = 1;
        boolean exit = false;
        while (!exit) {
            newLine = readLine();
            if (newLine.startsWith("loop")) {
                loopLevel++;
            } else if (newLine.startsWith("endloop")) {
                loopLevel--;
                if (loopLevel == 0) {
                    exit = true;
                }
            }
            fullStatement.append(newLine).append("\n");
        }
        if (fullStatement.length() > 0) {
            fullStatement.setLength(fullStatement.length() - 1);
        }
        LoopStatement loopStatement = new LoopStatement(new Scanner(fullStatement.toString()), context);
        statements.add(loopStatement);
    }

    public boolean usesAsFunction(String functionName) {
        List<AbstractStatement> statements = getStatements();
        for (AbstractStatement statement : statements) {
            if (statement.usesAsFunction(functionName)) {
                return true;
            }
        }
        return false;
    }

    public final List<AbstractStatement> getStatements() {
        return Collections.unmodifiableList(statements);
    }

    public final List<CallStatement> getCallStatements() {
        List<CallStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof CallStatement) {
                statementsList.add((CallStatement) statement);
            }
        }
        return statementsList;
    }

    public final List<LoopStatement> getLoopStatements() {
        List<LoopStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof LoopStatement) {
                statementsList.add((LoopStatement) statement);
            }
        }
        return statementsList;
    }

    public final List<IfStatement> getIfStatements() {
        List<IfStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof IfStatement) {
                statementsList.add((IfStatement) statement);
            }
        }
        return statementsList;
    }

    public final List<SetStatement> getSetStatements() {
        List<SetStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof SetStatement) {
                statementsList.add((SetStatement) statement);
            }
        }
        return statementsList;
    }

    public final List<LocalStatement> getLocalStatements() {
        List<LocalStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof LocalStatement) {
                statementsList.add((LocalStatement) statement);
            }
        }
        return statementsList;
    }

    public final List<ExitWhenStatement> getExitWhenStatements() {
        List<ExitWhenStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof ExitWhenStatement) {
                statementsList.add((ExitWhenStatement) statement);
            }
        }
        return statementsList;
    }

    public final List<ReturnStatement> getReturnStatements() {
        List<ReturnStatement> statementsList = new ArrayList<>();
        for (AbstractStatement statement : statements) {
            if (statement instanceof ReturnStatement) {
                statementsList.add((ReturnStatement) statement);
            }
        }
        return statementsList;
    }

    /**
     * Combines this AST Node with another and then checks
     * for errors. Gracefully handles function main.
     *
     * @param other Other AST node to combine
     * @return Merged AST nodes
     */
    @Override
    public final AbstractNode merge(AbstractNode other) {
        Statements otherStatements = (Statements) other;
        List<AbstractStatement> newStatements = new ArrayList<>();
        newStatements.addAll(this.getLocalStatements());
        newStatements.addAll(otherStatements.getLocalStatements());
        for (AbstractStatement statement : statements) {
            if (!(statement instanceof LocalStatement)) {
                newStatements.add(statement);
            }
        }
        for (AbstractStatement statement : otherStatements.statements) {
            if (!(statement instanceof LocalStatement)) {
                newStatements.add(statement);
            }
        }
        return new Statements(newStatements, context);
    }
}
