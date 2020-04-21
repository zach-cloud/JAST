package services;

import interfaces.IHashService;
import interfaces.IOutputService;
import model.InputModel;
import stringHash.SStrHash2;

/**
 * Represents a service to hash a value and display to the user
 */
public class HashService implements IHashService {

    private IOutputService outputService;

    /**
     * Initializes this has service with an output service
     */
    public HashService() {
        this.outputService = new OutputService();
    }

    public HashService(IOutputService outputService) {
        this.outputService = outputService;
    }

    /**
     * Hashes the given value from the user input and displays
     *
     * @param input Formatted user input
     */
    @Override
    public void runHash(InputModel input) {
        outputService.print("Result: " + SStrHash2.hash(input.getPlaintext()));
    }
}
