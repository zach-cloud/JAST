package interfaces;

import java.util.Scanner;

/**
 * A class that can be used before sending data into the abstract syntax tree
 * This will correct some parts of the jass code (like comments and spacing)
 * so that it can be read without error.
 */
public interface IPreprocessFileService {

    /**
     * Preprocesses the given scanner input
     * Removing comments and normalizing spacing/etc.
     *
     * @param inputScanner  Scanner with data containing the user's input
     * @return New scanner with preprocessed text
     */
    Scanner preprocessFile(Scanner inputScanner);
}
