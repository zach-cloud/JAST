package nodes.functions;

import exception.ParsingException;
import interfaces.IFunctionRenameable;
import interfaces.IVariableRenameable;
import nodes.AbstractNode;
import tree.TreeContext;

import java.util.Scanner;

/**
 * Function Declaration line (i.e. function x takes y returns z)
 */
public abstract class GenericDeclaration extends AbstractNode {

    private String name;
    private Inputs inputs;
    private Output output;
    private boolean constant;
    private String accessModifier;

    private String startText;

    public void setStartText(String startText) {
        this.startText = startText;
    }

    /**
     * Sets up this node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     */
    public GenericDeclaration(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public GenericDeclaration(TreeContext context, String name, Inputs inputs, Output output, boolean constant, String accessModifier) {
        super(context);
        this.name = name;
        this.inputs = inputs;
        this.output = output;
        this.constant = constant;
        this.accessModifier = accessModifier;
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
        if(accessModifier != null && !accessModifier.isEmpty()) {
            builder.append(accessModifier + " ");
        }
        if(constant) {
            builder.append("constant ");
        }
        builder.append(startText + " ").append(name).append(" ").append(inputs.toString()).append(" ").append(output.toString());
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
        return this.toString();
    }

    /**
     * Parse the JASS code contained in the Scanner into a model object
     */
    @Override
    protected final void readNode() {
        String line = readLine();
        if(line.startsWith("public ")) {
            accessModifier = "public";
            line = line.substring(accessModifier.length() + 1);
        } else if(line.startsWith("private ")) {
            accessModifier = "private";
            line = line.substring(accessModifier.length() + 1);
        }
        if(line.startsWith("constant ")) {
            line = line.substring("constant ".length());
            this.constant = true;
        } else {
            this.constant = false;
        }
        if(startText == null) {
            throw new ParsingException("Read a node without setting startText");
        }
        if(!line.startsWith(startText + " ")) {
            throw new ParsingException("Not a " + startText + " declaration: " + line);
        }
        line = line.substring(1 + startText.length());
        String name = line.substring(0, line.indexOf(" "));
        line = line.substring(name.length());
        String inputs = line.substring(0, line.indexOf("returns"));
        line = line.substring(inputs.length());
        String outputs = line;

        this.name = name;
        this.inputs = new Inputs(new Scanner(inputs), context);
        this.output = new Output(new Scanner(outputs), context);
    }

    public final String getName() {
        return name;
    }

    public final Inputs getInputs() {
        return inputs;
    }

    public final Output getOutput() {
        return output;
    }

    public boolean isConstant() {
        return constant;
    }

    public String getAccessModifier() {
        return accessModifier;
    }
}
