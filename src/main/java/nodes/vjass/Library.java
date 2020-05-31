package nodes.vjass;

import com.sun.javafx.scene.control.behavior.SliderBehavior;
import exception.ParsingException;
import nodes.AbstractNode;
import nodes.j.Script;
import tree.TreeContext;

import java.util.Scanner;

public class Library extends AbstractNode {

    private String name;
    private String initializer;
    private Script innerScript;

    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     * @param context
     */
    public Library(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public Library(TreeContext context) {
        super(context);
    }

    /**
     * Creates a new node given the input
     *
     * @param input   JASS Code
     * @param context
     */
    public Library(String input, TreeContext context) {
        super(input, context);
    }

    /**
     * Sets up any class-level variables before
     * performing the node reading.
     */
    @Override
    protected void setupVariables() {
        super.setupVariables();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected void readNode() {
        StringBuilder libraryContents = new StringBuilder();
        String line = readLine();
        if(line.startsWith("library ")) {
            line = line.substring("library ".length());
            if(line.contains(" ")) {
                name = line.substring(0, line.indexOf(" "));
            } else {
                name = line;
            }
            if(line.contains("initializer ")) {
                initializer = line.substring(line.indexOf("initializer ") + "initializer ".length());
            }
            while(!line.equalsIgnoreCase("endlibrary")) {
                line = readLine();
                if(!line.equalsIgnoreCase("endlibrary")) {
                    libraryContents.append(line).append("\n");
                }
            }
            removeFinalCharacter(1, libraryContents);
            innerScript = new Script(new Scanner(libraryContents.toString()), context);
        } else {
            throw new ParsingException("Library did not start with library flag");
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
        builder.append("library ").append(name);
        if(initializer != null && !initializer.isEmpty()) {
            builder.append(" initializer ").append(initializer);
        }
        builder.append("\n");
        builder.append(innerScript.toString());
        builder.append("\nendlibrary");
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
        return null;
    }
}
