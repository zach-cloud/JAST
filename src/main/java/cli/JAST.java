package cli;

import exception.*;
import interfaces.ICommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

/**
 * The primary command loop of the program
 */
public final class JAST {

    /**
     * The Java package where command classes are located
     */
    public static final String COMMAND_PACKAGE = "command.";

    private Scanner in;

    /**
     * This enum should have every class name from
     * the Commands package.
     */
    enum Commands {
        HelpCommand,
        MergeCommand,
        QuitCommand,
        MergeDedupCommand,
        RenameVariableCommand,
        RenameFunctionCommand,
        HashBreakCommand,
        HashCommand,
        JJCPCommand,
        JJCPDCommand,
        NZCPCommand,
        NZCPDCommand,
        ReplaceStringCommand,
        OptimizeGuiCommand
    }

    /**
     * Starts the program main loop, asking user for input
     * until the user quits.
     */
    public void run() {
        if(in == null) {
            in = new Scanner(System.in);
        }
        System.out.println("Enter a command (help for helpfile, quit to exit)");

        // Loop can be broken by Quit command.
        while (!JASTState.quitDesired) {
            try {
                System.out.print("> ");
                String line = in.nextLine();
                for (Commands cmd : Commands.values()) {
                    // Disgusting class loader abuse to load all of our command
                    // and execute if they desired that specific command.
                    Object commandClass = Class.forName(COMMAND_PACKAGE + cmd).getDeclaredConstructor().newInstance();
                    ICommand iCommand = (ICommand) commandClass;
                    if (iCommand.isCommend(line)) {
                        iCommand.execute(line);
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
                // catch any class loader problems
                System.out.println("Internal error.");
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            } catch (SyntaxErrorException ex) {
                System.out.println("Syntax error encountered when merging.");
                System.out.println("(try merge-d)");
                System.out.println(ex.getMessage());
            } catch (ParsingException ex) {
                System.out.println("Unable to parse syntax tree");
                System.out.println(ex.getMessage());
            } catch (WritingException ex) {
                System.out.println("Error writing result to file");
                System.out.println(ex.getMessage());
            } catch (MergeFailureException ex) {
                System.out.println("Code merge failed");
                System.out.println(ex.getMessage());
            } catch (RenameFailureException ex) {
                System.out.println("Code rename failed");
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setScanner(Scanner in) {
        this.in = in;
    }
}
