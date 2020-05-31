package nodes.vjass;

import tree.TreeContext;

import java.util.Scanner;

public class Scope extends ScriptContainerInitializer {


    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     * @param context
     */
    public Scope(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
        this.startText = "scope";
        this.endText = "endscope";
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public Scope(TreeContext context) {
        super(context);
        this.startText = "scope";
        this.endText = "endscope";
    }

    @Override
    protected void setupVariables() {
        super.setupVariables();
        this.startText = "scope";
        this.endText = "endscope";
    }
}
