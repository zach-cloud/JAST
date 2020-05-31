package nodes.vjass;

import tree.TreeContext;

import java.util.Scanner;

public class Struct extends ScriptContainer {

    private String name;
    private String lineFlags;

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
    protected void parseNameLine(String line) {
        if(line.contains(" ")) {
            lineFlags = line.substring(line.indexOf(" "));
        }
    }

    @Override
    protected void buildFlags(StringBuilder builder) {
        if(lineFlags != null && !lineFlags.isEmpty()) {
            builder.append(lineFlags);
        }
    }

    @Override
    protected void setupVariables() {
        super.setupVariables();
        this.startText = "struct";
        this.endText = "endstruct";
    }
}
