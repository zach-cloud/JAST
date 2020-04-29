package nodes.wts;

import nodes.AbstractNode;
import exception.ParsingException;
import tree.TreeContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a File that contains many WTS Strings
 */
public class WtsStringsFile extends AbstractNode {

    private List<WtsString> strings;

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public WtsStringsFile(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected void setupVariables() {
        this.strings = new ArrayList<>();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected void readNode() {
        String inputWord = "";
        StringBuilder builder = null;
        boolean readingWtsString = false;

        try {
            // Continually read wts string until none remain
            while (hasNextLine()) {
                inputWord = readLine();
                if(!readingWtsString) {
                    if (!inputWord.isEmpty()) {
                        // We located text, so that begins a wts string
                        readingWtsString = true;
                        builder = new StringBuilder();
                        builder.append(inputWord).append("\n");
                    }
                } else {
                    builder.append(inputWord).append("\n");
                    if(inputWord.contains(WtsString.CLOSE_CHARACTER)) {
                        // Found the end of this wts string to parse it.
                        readingWtsString = false;
                        WtsString newString = new WtsString(new Scanner(builder.toString()), context);
                        strings.add(newString);
                    }
                }
            }
        } catch (Exception ex) {
            throw new ParsingException("Could not parse the WTS File: " + ex.getMessage());
        }
    }

    /**
     * Converts this node back to its original form.
     * Indentation is not added.
     *
     * @return Original form of this node (code or string)
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(WtsString string : strings) {
            builder.append(string.toString()).append("\n");
        }
        removeFinalCharacter(builder);
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
        for(WtsString string : strings) {
            addTabs(builder, indentationLevel);
            builder.append(string.toFormattedString(indentationLevel+1)).append("\n");
        }
        removeFinalCharacter(builder);
        return builder.toString();
    }

    public List<WtsString> getStrings() {
        return Collections.unmodifiableList(strings);
    }
}
