package interfaces;

import model.InputModel;

/**
 * Represents a service that will help break a SStrHash2 value
 */
public interface IHashBreakService {

    /**
     * Sets the hash to be broken to the hash value of the input
     *
     * @param input Formatted user input
     */
    void setHash(InputModel input);

    /**
     * Runs the hash breaking service and displays result to user
     */
    String runBreak();
}
