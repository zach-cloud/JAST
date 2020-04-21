package services;

import interfaces.IPreprocessFileService;

import java.util.Scanner;

/**
 * A class that can be used before sending data into the abstract syntax tree
 * This will correct some parts of the jass code (like comments and spacing)
 * so that it can be read without error.
 */
public class PreprocessFileService implements IPreprocessFileService {

    private String text;

    /**
     * Preprocesses the given scanner input
     * Removing comments and normalizing spacing/etc.
     *
     * @param inputScanner  Scanner with data containing the user's input
     * @return New scanner with preprocessed text
     */
    @Override
    public Scanner preprocessFile(Scanner inputScanner) {
        StringBuilder completeText = new StringBuilder();
        // Read the scanner contents into a StringBuilder
        // We will use this StringBuilder to preprocess the code file
        while(inputScanner.hasNextLine()) {
            completeText.append(inputScanner.nextLine()).append("\n");
        }
        // Remove final newline
        if(completeText.length()>0) {
            completeText.setLength(completeText.length()-1);
        }
        this.text = filter(completeText.toString());
        return new Scanner(text);
    }

    /**
     * Removes unnecessary spacing, comments, and newlines from file
     *
     * @param input Input string
     * @return      Input string with unnecessary characters filtered
     */
    private String filter(String input) {
        StringBuilder currentLineOfCode = new StringBuilder();
        StringBuilder assembledCode = new StringBuilder();
        boolean quoted = false; // Set to true if we encounter an unescaped quote
        boolean commented = false; // Set to true if code is commented out
        int numEscapeChars = 0;
        // This logical parsing affects most code text, but does
        // not affect stuff in quotes (text literals that should
        // be left alone)

        for(char c : input.toCharArray()) {
            if(c == '\\') {
                numEscapeChars++;
            }
            if(c == '"') {
                // Handle the quote character
                if(!commented) {
                    // Handle escaped quotes
                    if (numEscapeChars == 0 || numEscapeChars % 2 == 0) {
                        quoted = !quoted;
                    }
                    currentLineOfCode.append(c);
                }
            } else if(c == '\n') {
                // Detect end-of-line if it's not in quotes
                if (!quoted) {
                    // Found the end of our current line of code
                    currentLineOfCode.append(c);
                    commented = false;
                    // Add this line to the assembled lines and clear current
                    String line = currentLineOfCode.toString();
                    currentLineOfCode.setLength(0);
                    assembledCode.append(line.trim()).append("\n");
                } else {
                    // Ignore commented code
                    if(!commented) {
                        currentLineOfCode.append("|n");
                    }
                }
            } else if(currentLineOfCode.length() > 0 && c == 'n' && currentLineOfCode.charAt(currentLineOfCode.length()-1) == '\\') {
                // Handle literal characters "\n" as newline
                // Is this necessary?
                // Try regression testing without it
                if(!commented) {
                    if (!quoted) {
                        currentLineOfCode.append("\n");
                    } else {
                        currentLineOfCode.append(c);
                    }
                }
            } else if (currentLineOfCode.length() > 0 && c == '/' && currentLineOfCode.charAt(currentLineOfCode.length()-1) == '/') {
                // Detect when code gets commented out and ignore that code
                if(!commented) {
                    if (!quoted) {
                        commented = true;
                        // Remove the comment character
                        currentLineOfCode.setLength(currentLineOfCode.length() - 1);
                    } else {
                        currentLineOfCode.append(c);
                    }
                }
            } else {
                // Ignore commented out code
                if(!commented) {
                    currentLineOfCode.append(c);
                }
            }
            if(c != '\\') {
                numEscapeChars = 0;
            }
        }
        // Add the final line of code we found
        String line = currentLineOfCode.toString();
        currentLineOfCode.setLength(0);
        assembledCode.append(line.trim()).append("\n");
        // Final pre-processing before we give back code
        String constructed = assembledCode.toString();
        // Remove empty lines
        while(constructed.contains("\n\n")) {
            constructed = constructed.replace("\n\n","\n");
        }
        // If the code starts with a newline, then remove it
        if(constructed.startsWith("\n")) {
            constructed = constructed.substring(1);
        }
        // If the code ends with a newline, then remove it
        if(constructed.endsWith("\n")) {
            constructed = constructed.substring(0, constructed.length()-1);
        }
        return constructed.trim();
    }

    /**
     * Makes a new Scanner containing the filtered text
     *
     * @return  Scanner for filtered text
     */
    public final Scanner getScanner() {
        return new Scanner(text);
    }
}
