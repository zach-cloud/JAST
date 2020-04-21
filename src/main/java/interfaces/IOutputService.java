package interfaces;

/**
 * Helper to write output to the user.
 * Can be used to write to a logfile at the same time.
 */
public interface IOutputService {

    /**
     * Prints the message to the user
     *
     * @param output    Message
     */
    void print(String output);
}
