package services;

import helper.CheatpackLoader;
import interfaces.IInputParserService;
import interfaces.ISyntaxTree;
import model.InputModel;
import tree.SyntaxTree;

import java.io.File;

/**
 * Service to split a given input line into multiple entries
 */
public class InputParserService implements IInputParserService {

    /**
     * Splits the given input into an input model
     *
     * @param type          How we should split this input
     * @param inputLine     The input line
     * @return              Input model from line
     */
    @Override
    public InputModel splitInput(SplitType type, String inputLine) {
        if(type == SplitType.CHEATPACK) {
            return splitCheatpack(inputLine);
        } else if(type == SplitType.HASH) {
            return splitHash(inputLine);
        } else if(type == SplitType.HASHBREAK) {
            return splitHashbreak(inputLine);
        } else if(type == SplitType.MERGE) {
            return splitMerge(inputLine);
        } else if(type == SplitType.RENAME) {
            return splitRename(inputLine);
        } else if(type == SplitType.OPTIMIZE) {
            return splitOptimize(inputLine);
        } else if(type == SplitType.RAWCODE) {
            return splitRawcode(inputLine);
        } else {
            throw new IllegalArgumentException("Unknown type: " + type.name());
        }
    }

    private ISyntaxTree readTreeByName(String name) {
        if(CheatpackLoader.canLoadCheatpackByName(name)) {
            return SyntaxTree.readTree(CheatpackLoader.loadCheatpackByName(name));
        } else {
            return SyntaxTree.readTree(new File(name));
        }
    }

    /**
     * Splits the input line into an input model
     * to be used in cheatpack inserting
     *
     * @param inputLine Input line containing:
     *                  1) Input file path
     *                  2) Desired activator
     *                  3) Ouptut file path
     * @return         Input model
     */
    private InputModel splitCheatpack(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setTree1(readTreeByName(parts[1]));
        inputModel.setActivator(parts[2]);
        inputModel.setOutputPath(parts[3]);
        return inputModel;
    }

    /**
     * Splits the input line into an input model
     * to be used in hash breaking
     *
     * @param inputLine Input line containing:
     *                  1) Hash to break
     * @return         Input model
     */
    private InputModel splitHashbreak(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setHash(parts[1]);
        return inputModel;
    }

    /**
     * Splits the input line into an input model
     * to be used in hashing
     *
     * @param inputLine Input line containing:
     *                  1) String to hash
     * @return         Input model
     */
    private InputModel splitHash(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setPlaintext(parts[1]);
        return inputModel;
    }

    /**
     * Splits the input line into an input model
     * to be used in merging
     *
     * @param inputLine Input line containing:
     *                  1) Script 1 input file path
     *                  2) Script 2 input file path
     *                  3) Output file path
     * @return         Input model
     */
    private InputModel splitMerge(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setTree1(readTreeByName(parts[1]));
        inputModel.setTree2(readTreeByName(parts[2]));
        inputModel.setOutputPath(parts[3]);
        return inputModel;
    }

    /**
     * Splits the input line into an input model
     * to be used in renaming
     *
     * @param inputLine Input line containing:
     *                  1) Input file path
     *                  2) Existing name of entity to replace
     *                  3) Desired name of entity to replace
     *                  4) Output file path
     * @return         Input model
     */
    private InputModel splitRename(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setTree1(SyntaxTree.readTree(new File(parts[1])));
        inputModel.setOldName(parts[2]);
        inputModel.setNewName(parts[3]);
        inputModel.setOutputPath(parts[4]);
        return inputModel;
    }

    /**
     * Splits the input line into an input model
     * to be used in optimizing gui
     *
     * @param inputLine Input line containing:
     *                  1) Input file path
     *                  2) Output file path
     * @return         Input model
     */
    public InputModel splitOptimize(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setTree1(SyntaxTree.readTree(new File(parts[1])));
        inputModel.setOutputPath(parts[2]);
        return inputModel;
    }

    /**
     * Splits the input line into an input model
     * to be used in generating rawcodes
     *
     * @param inputLine Input line containing:
     *                  1) Input file path
     *                  2) Output file path
     *                  3) [Optional] WTS File path
     * @return         Input model
     */
    public InputModel splitRawcode(String inputLine) {
        InputModel inputModel = new InputModel();
        String[] parts = inputLine.split("\\s");
        inputModel.setRawcodeInput(parts[1]);
        inputModel.setRawcodeOutput(parts[2]);
        if(parts.length > 3) {
            inputModel.setWtsFile(parts[3]);
        }
        return inputModel;
    }
}
