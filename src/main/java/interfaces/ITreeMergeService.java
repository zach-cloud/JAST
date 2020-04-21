package interfaces;

import model.InputModel;

/**
 * Helper service for merging code files.
 */
public interface ITreeMergeService {

    /**
     * Merges code files together based on input.
     *
     * @param inputModel User's formatted input
     * @param dedupe     True if code should be de-duped before inserting
     */
    void merge(InputModel inputModel, boolean dedupe);

    /**
     * Adds NZCP to a map.
     *
     * @param dedupe            True if code should be de-duped before inserting
     * @param input             User's formatted input
     */
    public void applyNzcp(boolean dedupe, InputModel input);

    /**
     * Adds JJCP to a map.
     *
     * @param dedupe            True if code should be de-duped before inserting
     * @param input             User's formatted input
     */
    public void applyJjcp(boolean dedupe, InputModel input);
}
