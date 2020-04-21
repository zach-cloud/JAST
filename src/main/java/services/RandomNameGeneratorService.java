package services;

import interfaces.IRandomNameGeneratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Used to generate random and unique variable/function names
 */
public class RandomNameGeneratorService implements IRandomNameGeneratorService {

    private static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int LENGTH = 12;
    private List<String> usedNames;
    private Random random;

    /**
     * Initialized the random name generator
     */
    public RandomNameGeneratorService() {
        this.random = new Random();
        this.usedNames = new ArrayList<>();
    }

    /**
     * Produces the next unique random name
     *
     * @return  Unique random name
     */
    @Override
    public String next() {
        StringBuilder name = new StringBuilder();
        for(int i = 0; i < LENGTH; i++) {
            name.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        if(usedNames.contains(name.toString())) {
            return next();
        } else {
            usedNames.add(name.toString());
            return name.toString();
        }
    }
}
