package nodes.functions;

import exception.ParsingException;
import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import tree.TreeContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores an argument for a function call or statement.
 * This decomposes the argument into the most basic form possible.
 *
 * This class logically splits an argument into three basic forms:
 *
 * - Basic argument: cannot be broken down any further and calls no method
 * - Function call: a single, standalone function call
 * - Aggregation: other arguments joined by an operator (for example +)
 */
public final class Argument extends AbstractNode implements IFunctionRenameable, IVariableRenameable {

    /**
     * Basic Argument is something that stands alone, for example
     * a literal or a variable reference
     * i.e. "5", or "myVariable"
     */
    private String basicArgument;
    /**
     * A function call is an atomic call to a function that
     * may or may not return a value.
     * i.e. myFunction(5)
     */
    private FunctionCall functionCall;
    /**
     * An aggregation is a combination of two or more
     * basic arguments and/or function calls.
     * i.e. myFunction + 5 + 3
     */
    private List<Argument> aggregation;
    /**
     * The separator between the aggregation entries.
     */
    private String operator;
    /**
     * If we removed parenthesis to begin with,
     * we want to add them back on at the end.
     */
    private boolean hasParenthesis;
    /**
     * Represents the argument of not (notPart)
     */
    private Argument notPart;

    /**
     * Operators that cen separate an aggregation.
     * Note the spaces in the operator is important
     * Or else we can't differentiate between < and <= for example
     */
    private static String[] OPERATORS = {"+", "-", "/", "*", " and ", " or ", "> ", "< ", ">=", "<=", "==", "!=", " not "};
    /**
     * Some additional characters that aren't really
     * separators for an aggregation, but also can't be
     * in a function name.
     */
    private static String[] INVALID_FUNCTION_CHARACTERS = {"\"", "\\", ")", "(", "[", "]", "\n", " "};

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public Argument(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public Argument(String basicArgument, FunctionCall functionCall, List<Argument> aggregation, String operator, Argument notPart, boolean hasParenthesis, TreeContext context) {
        super(context);
        this.basicArgument = basicArgument;
        this.functionCall = functionCall;
        this.aggregation = aggregation;
        this.operator = operator;
        this.notPart = notPart;
        this.hasParenthesis = hasParenthesis;
        verifyState();
    }

    /**
     * Determines whether or not this line is an
     * atomic function call, for example:
     *
     * myFunction(x) : is a function call.
     * myFunction(x) + 5 : is not a function call (it's an aggregation)
     *
     * @param line  Argument line
     * @return      True if function call; false if not.
     */
    private boolean isFunctionCall(String line) {
        if (line.contains("(") && line.contains(")")) {
            // Extract everything up to the first ( as the functon name
            String functionName = line.substring(0, line.indexOf("("));
            // Check function name for validity
            // If it fails these checks, it wasn't a function call
            for (String operator : OPERATORS) {
                if (functionName.contains(operator)) {
                    return false;
                }
            }
            for (String operator : INVALID_FUNCTION_CHARACTERS) {
                if (functionName.contains(operator)) {
                    return false;
                }
            }
            // Everything other than the function name should be the arguments
            String argumentsPart = line.substring(functionName.length());
            boolean quoted = false;
            boolean firstParenthesisFound = false;
            int parenthesisLevel = 0; // Keeps track of how many parenthesis deep we are in a line
            // Used to handle escape chars
            char lastChar1 = ' ';
            char lastChar2 = ' ';
            boolean shouldBeFinished = false;

            for (char c : argumentsPart.toCharArray()) {
                if (shouldBeFinished) {
                    // We should have reached the end of line but didn't
                    // Therefore this isn't an atomic function call
                    return false;
                }
                if (c == '\"') {
                    // Handle quotes appropriately, taking escape characters into account
                    if (lastChar1 != '\\') {
                        quoted = !quoted;
                    } else {
                        if (lastChar2 == '\\') {
                            quoted = !quoted;
                        }
                    }
                } else if (c == '(' && !quoted) {
                    // Non-quoted parenthesis means we started a function call
                    // and went one-parenthesis deeper than before
                    parenthesisLevel++;
                    firstParenthesisFound = true;
                } else if (c == ')' && !quoted) {
                    // Non-quoted end-parenthesis means we went up one
                    // parenthesis level and maybe finished the function call
                    parenthesisLevel--;
                    if (parenthesisLevel == 0 && firstParenthesisFound) {
                        // If we ended our function call, this needs to be the
                        // last char of the line to be valid.
                        // If not the last char of the line, this boolean will
                        // make it return false
                        shouldBeFinished = true;
                    }
                }
                // Update the last 2 characters to handle escape characters correctly
                lastChar2 = lastChar1;
                lastChar1 = c;
            }
            // If we reach the end here without returning false, that means it IS atomic
            return true;
        } else {
            // It doesn't have parenthesis at all so it's not a function call
            return false;
        }
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        String line = readLine();
        line = line.trim();
        line = formatSpacing(line);
        // Handle the annoying "is it < or <=" case by using spacing
        line = line.replaceAll("<^[\\=]", "< ");
        line = line.replaceAll(">^[\\=]", "> ");
        if (shouldTrim(line)) {
            // Maintain original parenthesis
            hasParenthesis = true;
            line = trimParenthesis(line).trim();
        } else {
            hasParenthesis = false;
        }
        if(line.startsWith("not ")) {
            line = " " + line;
        }
        List<String> splitParts = new ArrayList<>();
        StringBuilder splitPart = new StringBuilder();
        if (isFunctionCall(line)) {
            this.functionCall = new FunctionCall(new Scanner(line), context);
        } else {
            // If it's not a function call, try to make it an
            // aggregation first. If it's not, then make it a basic arg.
            boolean foundOperator = false;
            boolean quoted = false;
            String whichOperator = "";
            int parenthesisLevel = 0;
            char lastChar1 = ' ';
            char lastChar2 = ' ';
            for (char c : line.toCharArray()) {
                splitPart.append(c);
                // Handle quotes and escapes quotes
                if (c == '\"') {
                    if (lastChar1 != '\\') {
                        quoted = !quoted;
                    } else {
                        if (lastChar2 == '\\') {
                            quoted = !quoted;
                        }
                    }
                } else if (c == '(') {
                    parenthesisLevel++;
                } else if (c == ')') {
                    parenthesisLevel--;
                } else if (parenthesisLevel == 0) {
                    for (String op : OPERATORS) {
                        // Try to split by every operator
                        // If we can split by this operator, then split
                        // the remainder of the entry by it as well.
                        if (splitPart.toString().endsWith(op) && !quoted && !foundOperator) {
                            foundOperator = true;
                            whichOperator = op + "";
                            removeFinalCharacter(op.length(), splitPart);
                            splitParts.add(splitPart.toString());
                            splitPart.setLength(0);
                        } else if (foundOperator) {
                            // Split by already known operator
                            if ((c + "").equals(whichOperator) && !quoted && splitPart.length() > 0) {
                                removeFinalCharacter(op.length(), splitPart);
                                splitParts.add(splitPart.toString());
                                splitPart.setLength(0);
                            }
                        }
                    }
                }
                // Manage last characters for escaped quotes handling
                lastChar2 = lastChar1;
                lastChar1 = c;
            }
            if(splitPart.length()>0) {
                splitParts.add(splitPart.toString());
            }
            if (foundOperator) {
                // If we found an operator, then it's an aggregation
                this.operator = whichOperator.trim();
                int size = splitParts.size();
                for (String part : splitParts) {
                    // Handle empty part, for example "-1" splits into "", "1"
                    // But this is not an aggregation!
                    if (part == null || part.isEmpty()) {
                        size--;
                    }
                }
                // This is for empty part handling again.
                if (size >= 2) {
                    for (String part : splitParts) {
                        // Parse each sub-argument into arguments.
                        Argument argumentPart = new Argument(new Scanner(part), context);
                        this.aggregation.add(argumentPart);
                    }
                } else if(size == 1 && this.operator.equals("not")) {
                    notPart = new Argument(new Scanner(splitParts.get(1)), context);
                } else {
                    // We thought it was an aggregation, but it's actually
                    // a basic function. The line "-1" triggers this block.
                    this.basicArgument = line;
                }
            } else {
                // It couldn't be split, so it's not an aggregation.
                this.basicArgument = line;
            }
        }
        verifyState();
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
        if (functionCall != null) {
            return new Argument(basicArgument, (FunctionCall)functionCall.renameVariable(oldVariableName, newVariableName), aggregation, operator, notPart, hasParenthesis, context);
        } else if (basicArgument != null && !basicArgument.isEmpty()) {
            return new Argument(rename(basicArgument, oldVariableName, newVariableName), functionCall, aggregation, operator, notPart, hasParenthesis, context);
        } else if (!aggregation.isEmpty()) {
            List<Argument> newAggregation = new ArrayList<>();
            for (Argument arg : aggregation) {
                newAggregation.add((Argument)arg.renameVariable(oldVariableName, newVariableName));
            }
            return new Argument(basicArgument, functionCall, newAggregation, operator, notPart, hasParenthesis, context);
        } else if(notPart != null) {
            return new Argument(basicArgument, functionCall, aggregation, operator, (Argument)notPart.renameVariable(oldVariableName, newVariableName), hasParenthesis, context);

        }
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
        if (functionCall != null) {
            return new Argument(basicArgument, (FunctionCall)functionCall.renameFunction(oldFunctionName, newFunctionName), aggregation, operator, notPart, hasParenthesis, context);
        } else if (basicArgument != null && !basicArgument.isEmpty()) {
            return new Argument(rename(basicArgument, oldFunctionName, newFunctionName), functionCall, aggregation, operator, notPart, hasParenthesis, context);
        } else if (!aggregation.isEmpty()) {
            List<Argument> newAggregation = new ArrayList<>();
            for (Argument arg : aggregation) {
                newAggregation.add((Argument)arg.renameFunction(oldFunctionName, newFunctionName));
            }
            return new Argument(basicArgument, functionCall, newAggregation, operator, notPart, hasParenthesis, context);
        } else if(notPart != null) {
            return new Argument(basicArgument, functionCall, aggregation, operator, (Argument)notPart.renameFunction(oldFunctionName, newFunctionName), hasParenthesis, context);
        }
        return this;
    }

    public Argument inline(String functionName, String newText) {
        if (functionCall != null) {
            return new Argument(basicArgument, functionCall.inline(functionName, newText), aggregation, operator, notPart, hasParenthesis, context);
        } else if (basicArgument != null && !basicArgument.isEmpty()) {
            return this;
        } else if (!aggregation.isEmpty()) {
            List<Argument> newAggregation = new ArrayList<>();
            for (Argument arg : aggregation) {
                newAggregation.add((Argument)arg.inline(functionName, newText));
            }
            return new Argument(basicArgument, functionCall, newAggregation, operator, notPart, hasParenthesis, context);
        } else if(notPart != null) {
            return new Argument(basicArgument, functionCall, aggregation, operator, notPart.inline(functionName, newText), hasParenthesis, context);
        }
        return this;
    }

    public boolean usesAsFunction(String functionName) {
        if (functionCall != null) {
            return functionCall.usesAsFunction(functionName);
        } else if (basicArgument != null && !basicArgument.isEmpty()) {
            return basicArgument.contains("function " + functionName);
        } else if (!aggregation.isEmpty()) {
            for (Argument arg : aggregation) {
                if(arg.usesAsFunction(functionName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected final void setupVariables() {
        this.aggregation = new ArrayList<>();
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
        if (hasParenthesis) {
            // Add back on the trimmed parenthesis, if required.
            built.append("(");
        }
        if (basicArgument != null && aggregation.isEmpty() && functionCall == null) {
            return basicArgument;
        } else if (!aggregation.isEmpty()) {
            for (Argument aggregate : aggregation) {
                built.append(aggregate.toString()).append(" ").append(operator).append(" ");
            }
            removeFinalCharacter(operator.length() + 2, built);
        } else if (functionCall != null) {
            return functionCall.toString();
        } else if(notPart != null) {
            return "not (" + notPart.toString() + ")";
        } else {
            return "";
        }
        if (hasParenthesis) {
            // Add back on the trimmed parenthesis, if required.
            built.append(")");
        }
        // Trim spaces from and/or because we added an extra one on.
        return built.toString().replace("  and  ", " and ").replace("  or  ", " or ");
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

    /**
     * Ensures the state of this argument is not corrupted.
     * Should not happen under regular usage.
     */
    private void verifyState() {
        if (basicArgument != null) {
            if (!(aggregation.isEmpty() && functionCall == null)) {
                throw new ParsingException("Failed internal validity (1) check: " + this.toString());
            }
        } else if (!aggregation.isEmpty()) {
            if (!(basicArgument == null) && functionCall == null) {
                throw new ParsingException("Failed internal validity (2) check: " + this.toString());
            }
        } else if (functionCall != null) {
            if (!(basicArgument == null && aggregation.isEmpty())) {
                throw new ParsingException("Failed internal validity (3) check: " + this.toString());
            }
        } else if(notPart == null) {
            throw new ParsingException("Failed internal validity (4) check: " + this.toString());
        }
    }

    public boolean isNot() {
        return notPart != null;
    }

    public Argument getNotPart() {
        return notPart;
    }
}
