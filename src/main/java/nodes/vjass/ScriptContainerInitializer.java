package nodes.vjass;

import tree.TreeContext;

import java.util.Scanner;

public abstract class ScriptContainerInitializer extends ScriptContainer {

    private String initializer;

    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     * @param context
     */
    public ScriptContainerInitializer(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public ScriptContainerInitializer(TreeContext context) {
        super(context);
    }

    @Override
    protected void parseNameLine(String line) {
        if (line.contains("initializer ")) {
            this.initializer = line.substring(line.indexOf("initializer ") + "initializer ".length());
        }
    }

    @Override
    protected void buildFlags(StringBuilder builder) {
        if (initializer != null && !initializer.isEmpty()) {
            builder.append(" initializer ").append(initializer);
        }
    }
}
