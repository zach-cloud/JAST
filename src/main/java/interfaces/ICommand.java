package interfaces;

/**
 * Represents a generic command that can be executed.
 */
public interface ICommand {

    /**
     * Determines whether the given input should execute this command
     *
     * @param input User input
     * @return      True if matches command; false if not.
     */
    boolean isCommend(String input);

    /**
     * Executes this command using the user's input.
     *
     * @param input User input
     */
    void execute(String input);
}
