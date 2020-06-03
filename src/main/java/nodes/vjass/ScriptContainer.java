package nodes.vjass;

import exception.ParsingException;
import nodes.AbstractNode;
import nodes.j.Script;
import tree.TreeContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class ScriptContainer extends AbstractNode {

    protected String name;
    private Script innerScript;

    protected String startText;
    protected String endText;

    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     * @param context
     */
    public ScriptContainer(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public ScriptContainer(TreeContext context) {
        super(context);
    }

    /**
     * Creates a new node given the input
     *
     * @param input   JASS Code
     * @param context
     */
    public ScriptContainer(String input, TreeContext context, String startText, String endText) {
        super(input, context);
        this.startText = startText;
        this.endText = endText;
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected void setupVariables() {
        super.setupVariables();
    }

    protected abstract void parseNameLine(String line);

    protected abstract void buildFlags(StringBuilder builder);

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected void readNode() {
        StringBuilder contents = new StringBuilder();
        String line = readLine();
        if(line.startsWith(startText + " ")) {
            line = line.substring((startText + " ").length());
            if(line.contains(" ")) {
                name = line.substring(0, line.indexOf(" "));
            } else {
                name = line;
            }
            parseNameLine(line);
            while(!line.equalsIgnoreCase(endText)) {
                if(!hasNextLine()) {
                    throw new ParsingException("Did not find end of container: " + endText);
                }
                line = readLine();
                if(!line.equalsIgnoreCase(endText)) {
                    contents.append(line).append("\n");
                }
            }
            removeFinalCharacter(1, contents);
            innerScript = new Script(new Scanner(contents.toString()), context);
        } else {
            throw new ParsingException("Container did not start with " + startText + " flag");
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
        builder.append(startText + " ").append(name);
        buildFlags(builder);
        builder.append("\n");
        builder.append(innerScript.toString());
        builder.append("\n" + endText);
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
        addTabs(builder, indentationLevel-1);
        builder.append(startText + " ").append(name);
        buildFlags(builder);
        builder.append("\n");
        builder.append(innerScript.toFormattedString(indentationLevel));
        addTabs(builder, indentationLevel-1);
        builder.append("\n" + endText);
        return builder.toString();
    }
}
