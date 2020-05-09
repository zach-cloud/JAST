package nodes;

import exception.ParsingException;
import interfaces.IAbstractNode;
import tree.TreeContext;

import java.util.Scanner;

/**
 * Represents a node in the syntax tree
 * AST Nodes shall be immutable.
 */
public abstract class AbstractNode implements IAbstractNode {

    /**
     * Scanner to read lines from.
     * Should contain ONLY the code for this specific AST Node.
     */
    private Scanner fileScanner;

    protected TreeContext context;

    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public AbstractNode(Scanner inputScanner, TreeContext context) {
        this.fileScanner = inputScanner;
        this.context = context;
        this.setupVariables();
        this.readNode();
        this.verifyEndOfStream();
    }

    /**
     * No-args constructor used for creating from an existing
     */
    public AbstractNode(TreeContext context) {
        this.context = context;
    }

    /**
     * Creates a new node given the input
     *
     * @param input JASS Code
     */
    public AbstractNode(String input, TreeContext context) {
        this(new Scanner(input), context);
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    protected void setupVariables() {
        // Do nothing
    };

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    protected abstract void readNode();

    /**
     * Gets a word from the file input
     *
     * @return Next word
     */
    protected final String readLine() {
        return readLine(true);
    }

    protected final String readLine(boolean trim) {
        if(!hasNextLine()) {
            return "";
        }
        String line = fileScanner.nextLine();
        context.setLastLine(line);
        if(trim) {
            line = line.trim();
        }
        line = line.replace("\t", " ");
        if(line.startsWith("if(")) {
            line = line.substring(line.indexOf("if(") + "if(".length());
            line = "if (" + line;
        }
        if(line.startsWith("exitwhen")) {
            line = line.substring(line.indexOf("exitwhen") + "exitwhen".length());
            line = "exitwhen " + line;
        }
        if(line.startsWith("elseif")) {
            line = line.substring(line.indexOf("elseif") + "elseif".length());
            line = "elseif " + line;
        }
        return line;
    }

    /**
     * Called after reading to ensure that the scanner is all used up
     */
    private void verifyEndOfStream() {
        if (hasNextLine()) {
            throw new ParsingException("Expected EOF, got " + readLine());
        }
    }

    /**
     * Checks whether there's another word available
     *
     * @return True if has next word; false if not.
     */
    protected final boolean hasNextLine() {
        return fileScanner.hasNextLine();
    }

    /**
     * Safe method to delete final character from a StringBuilder.
     *
     * @param inputString StringBuilder with an extra newline
     */
    protected final void removeFinalCharacter(StringBuilder inputString) {
        if (inputString.length() > 0) {
            inputString.deleteCharAt(inputString.length() - 1);
        }
    }

    /**
     * Safe method to delete final character from a StringBuilder.
     *
     * @param count       How many characters to remove
     * @param inputString StringBuilder with an extra newline
     */
    protected final void removeFinalCharacter(int count, StringBuilder inputString) {
        for(int i = 0; i < count; i++) {
            removeFinalCharacter(inputString);
        }
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public abstract String toString();

    /**
     * Converts this node back to its original form.
     *
     * @param indentationLevel  Current indentation level
     * @return Original form of this node (code or string) with indentation
     */
    public abstract String toFormattedString(int indentationLevel);

    /**
     * Adds tab characters to this StringBuilder
     *
     * @param currentString Current Stringbuilder to add to
     * @param indentationLevel  Indentation level to add up to
     */
    protected void addTabs(StringBuilder currentString, int indentationLevel) {
        for(int i = 0; i < indentationLevel; i++) {
            currentString.append("    ");
        }
    }


    /**
     * Determines whether the line should have parenthesis trimmed from it
     *
     * @param origin    JASS Code
     * @return          true if parenthesis can be trimmed; false if not.
     */
    protected final boolean shouldTrim(String origin) {
        int parenthesisLevel = 0; // how many parenthesis deep we are
        boolean foundFirstParenthesis = false; // set to true when first ( is discovered
        boolean exitOnNext = false; // handles one additional iteration to make sure we don't exit false on lines that should be trimmed
        boolean quoted = false; // set to true when we discover a " in the file that isn't escaped
        char lastChar1 = ' '; // used to handle escape chars
        char lastChar2 = ' '; // used to handle escape chars. remember \\ is its own escape char
        // Logical parse character-by-character
        // This parse is required because lines like: (5000)+(100) should not get trimmed.
        // It's harder than it seems because of the existence of quotes and escape chars on those quotes.
        for (char c : origin.toCharArray()) {
            if(exitOnNext) {
                // Only exit false if there's another iteration after parenthesis gets closed out
                return false;
            }
            if (c == '\"') {
                // Handle escape characters on quotes
                if (lastChar1 != '\\') {
                    quoted = !quoted;
                } else {
                    if (lastChar2 == '\\') {
                        quoted = !quoted;
                    }
                }
            }
            if (c == '(') {
                // Parenthesis only counts as syntax if it's not quotes
                if (!quoted) {
                    parenthesisLevel++;
                    if (parenthesisLevel == 1) {
                        foundFirstParenthesis = true;
                    }
                }
            } else if (c == ')') {
                if (!quoted) {
                    parenthesisLevel--;
                    if (parenthesisLevel == 0 && foundFirstParenthesis) {
                        // Parenthesis got closed out, so this needs to be the end of line to be returned as true
                        // This condition makes sure there's no next character
                        // We can't return false here, or it always returns false even when it should get trimmed
                        exitOnNext = true;
                    }
                }
            }
            // Move over characters
            lastChar2 = lastChar1;
            lastChar1 = c;
        }
        // If we reached this point, we didn't exit out earlier on sooner closed parens
        // So we check if we can trim by making sure parenthesis exists
        return origin.startsWith("(") && origin.endsWith(")");
    }

    /**
     * Trims beginning and end parenthesis from line, if possible.
     *
     * @param origin    JASS Code
     * @return          JASS Code with parenthesis trimmed
     */
    protected final String trimParenthesis(String origin) {
        while(shouldTrim(origin)) {
            origin = origin.substring(1);
            origin = origin.substring(0, origin.length() - 1);
        }
        return origin;
    }

    /**
     * Format this line in the way we expect - we need to split by space later
     *
     * @param line  Line of code (condition)
     * @return      Formatted line of code
     */
    protected final String formatSpacing(String line) {
        line = line.replace(")and(", ") and (");
        line = line.replace(")or(", ") or (");
        line = line.replace(")and", ") and");
        line = line.replace(")or", ") or");
        line = line.replace("and(", "and (");
        line = line.replace("or(", "or (");
        line = line.replace("not(", "not (");
        line = line.replace(")not", ") not");
        return line;
    }

    protected final String rename(String original, String oldName, String newName) {
        if(original.equals(oldName)) {
            return newName;
        } else if(original.equals("function " + oldName)) {
            return "function " + newName;
        } else {
            return original;
        }
    }
}
