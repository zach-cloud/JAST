package services;

import command.HashBreakCommand;
import interfaces.IHashBreakService;
import interfaces.IOutputService;
import model.InputModel;
import stringHash.StringHashBreakerThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a service that will help break a SStrHash2 value
 */
public class HashBreakService implements IHashBreakService {

    private IOutputService outputService;
    private StringHashBreakerThread thread = null;

    /**
     * Initializes this class with an output service
     */
    public HashBreakService() {
        this.outputService = new OutputService();
    }

    public HashBreakService(IOutputService service) {
        this.outputService = service;
    }

    /**
     * Sets the hash to be broken to the hash value of the input
     *
     * @param input Formatted user input
     */
    @Override
    public void setHash(InputModel input) {
        this.thread = new StringHashBreakerThread(input.getHash());
    }

    /**
     * Runs the hash breaking service and displays result to user
     */
    @Override
    public String runBreak() {
        if(thread == null) {
            throw new IllegalArgumentException("Hash breaker service was not initialized and will not run");
        }
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                outputService.print("Attempts: " + thread.getAttempts());
            }
        }, 0, 5, TimeUnit.SECONDS);
        thread.run();
        outputService.print("Result: " + thread.getPlaintext());
        exec.shutdown();
        return thread.getPlaintext();
    }
}
