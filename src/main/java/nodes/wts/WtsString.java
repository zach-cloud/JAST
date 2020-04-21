package nodes.wts;

import nodes.AbstractNode;
import exception.ParsingException;
import tree.TreeContext;

import java.util.Scanner;

/**
 * Represents a single String entity inside a WTS File
 */
public final class WtsString extends AbstractNode {

    /**
     * Constants for WTS Parsing. A WTS File is a JSON entity with a String key above it.
     */
    public static final String COMMENT_CHARACTER = "//";
    public static final String OPEN_CHARACTER = "{";
    public static final String CLOSE_CHARACTER = "}";

    private StringBuilder key;
    private StringBuilder comment;
    private StringBuilder value;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public WtsString(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected final void setupVariables() {
        key = new StringBuilder();
        value = new StringBuilder();
        comment = new StringBuilder();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        String line = "";
        boolean reading = true; // set to false after we end json object
        boolean readingComment = false; // discovers and flags comments
        boolean readingKey = true; // set to true initially because key comes first
        boolean readingValue = false; // set to true when we discover intro json char {

        try {
            while (reading) {
                line = readLine();
                if (readingKey) {
                    if (line.startsWith(COMMENT_CHARACTER)) {
                        // We found a comment in the key section. This is valid.
                        readingKey = false;
                        readingComment = true;
                        comment.append(line).append("\n");
                    } else if (line.startsWith(OPEN_CHARACTER)) {
                        // We found the value section start
                        readingKey = false;
                        readingValue = true;
                    } else {
                        // It's still the key section
                        key.append(line).append("\n");
                    }
                } else if (readingComment) {
                    if (line.startsWith(OPEN_CHARACTER)) {
                        // Done with comment, we're now in value.
                        readingComment = false;
                        readingValue = true;
                    } else {
                        // Still reading comment
                        comment.append(line).append("\n");
                    }
                } else if (readingValue) {
                    if (line.startsWith(CLOSE_CHARACTER)) {
                        // We're done with the WTS String
                        readingValue = false;
                        reading = false;
                    } else {
                        // Keep reading value
                        value.append(line).append("\n");
                    }
                }
            }
            // Trim final newline from stringbuilders
            removeFinalCharacter(key);
            removeFinalCharacter(comment);
            removeFinalCharacter(value);
        } catch (Exception ex) {
            // Convert exception type
            throw new ParsingException("Could not parse the WTS String: " + ex.getMessage());
        }
    }

    public final String getKey() {
        return key.toString();
    }

    public final String getComment() {
        return comment.toString();
    }

    public final String getValue() {
        return value.toString();
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public final String toString() {
        StringBuilder builtString = new StringBuilder();
        builtString.append(getKey()).append("\n");
        if(getComment() != null) {
            builtString.append(getComment()).append("\n");
        }
        builtString.append("{").append("\n");
        builtString.append(value).append("\n");
        builtString.append("}");
        return builtString.toString();
    }
}
