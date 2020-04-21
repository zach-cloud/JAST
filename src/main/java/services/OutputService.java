package services;

import interfaces.IOutputService;

public class OutputService implements IOutputService {

    /**
     * Prints the message to the user
     *
     * @param output    Message
     */
    @Override
    public void print(String output) {
        System.out.println(output);
    }
}
