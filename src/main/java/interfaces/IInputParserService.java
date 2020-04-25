package interfaces;

import model.InputModel;

/**
 * Service to split a given input line into multiple entries
 */
public interface IInputParserService {

    /**
     * Defines how we should split this argument
     */
    enum SplitType {
        MERGE,
        CHEATPACK,
        RENAME,
        HASH,
        HASHBREAK,
        OPTIMIZE,
        RAWCODE,
        MPQEDIT
    }

    /**
     * Splits the given input into an input model
     *
     * @param type          How we should split this input
     * @param inputLine     The input line
     * @return              Input model from line
     */
    InputModel splitInput(SplitType type, String inputLine);
}
