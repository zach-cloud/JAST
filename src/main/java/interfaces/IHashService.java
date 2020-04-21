package interfaces;

import model.InputModel;

/**
 * Represents a service to hash a value and display to the user
 */
public interface IHashService {

    /**
     * Hashes the given value from the user input and displays
     *
     * @param input Formatted user input
     */
    void runHash(InputModel input);
}
