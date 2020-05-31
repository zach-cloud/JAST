package nodes.vjass;

import tree.TreeContext;

import java.util.Scanner;

public class Struct extends ScriptContainerInitializer {


    /**
     * Sets up this abstract node with a scanner to receive words.
     *
     * @param inputScanner Scanner containing JASS code
     * @param context
     */
    public Struct(Scanner inputScanner, TreeContext context) {
        super(inputScanner, context);
        this.startText = "struct";
        this.endText = "endstruct";
    }

    /**
     * No-args constructor used for creating from an existing
     *
     * @param context
     */
    public Struct(TreeContext context) {
        super(context);
        this.startText = "struct";
        this.endText = "endstruct";
    }

    @Override
    protected void setupVariables() {
        super.setupVariables();
        this.startText = "struct";
        this.endText = "endstruct";
    }
}
