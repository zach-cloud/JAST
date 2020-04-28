package command;

import interfaces.ICommand;
import interfaces.IOutputService;

/**
 * A command to show the help file to the user
 */
public final class HelpCommand extends AbstractComamnd implements ICommand {

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return      True if matches command; false if not.
     */
    @Override
    public boolean isCommend(String input) {
        return input.equals("help") || input.equals("h");
    }

    /**
     * Shows the help file
     *
     * @param input Empty string (ignored)
     */
    @Override
    public void execute(String input) {
        print("Type a command to use the program. The following are supported command:");
        print("\thelp : display this help file");
        print("\tnzcp [nz] <input.j> <activator> <output.j> : adds NZCP to map");
        print("\tnzcp-d [nzd] <input.j> <activator> <output.j> : adds NZCP to map and avoids collision");
        print("\tjjcp [jj] <input.j> <activator> <output.j> : adds JJCP to map");
        print("\tjjcp-d [jjd] <input.j> <activator> <output.j> : adds JJCP to map and avoids collision");
        print("\tmerge [m] <first.j> <second.j> <output.j> : merges scripts together");
        print("\tmerge-d [md] <first.j> <second.j> <output.j> : merges scripts together and avoids collisions");
        print("\treplacestring [rs] <input.j> <stringText> <newStringText> <output.j> : replaces string literal. Do not include quotes");
        print("\trenamevariable [rv] <script.j> <oldVariableName> <newVariableName> <output.j> : renames a variable in the script");
        print("\trenamefunction [rf] <script.j> <oldFunctionName> <newFunctionName> <output.j> : renames a function in the script");
        print("\thash [h] <text> : computes StringHash of input");
        print("\thashbreak [hb] <hash> : computes plaintext of StringHash. May take some time. Only uses printable characters.");
        print("\toptimize [opt] <war3map.j> <out.j> : optimizes some GUI code from the .j");
        print("\trawcodes [rc] <war3map.w3t> <out.txt> <optional: war3map.wts> : Retrieves rawcodes from map and formats them. If wts is provided, restores strings.");
        print("\textract [ext] <myMap.w3x> <outDirectory> : Exports critical map files from input file into output directory");
        // Non functional command.
        // print("\timport [imp] <myMap.w3x> <inputDirectory> : Imports all files from inputDirectory into the map");
        print("\tquit : quits the program");
        print("System is pre-programmed with cheatpacks: NZCP.j, JJCP.j, and FAI.j");
        print("When adding custom activators, if a space is desired, type: \"\\s\"");
    }
}
