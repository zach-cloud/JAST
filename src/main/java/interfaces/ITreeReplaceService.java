package interfaces;

import model.InputModel;
import services.TreeReplaceService;

/**
 * Helper service for replacing values from a syntax tree
 */
public interface ITreeReplaceService {

    /**
     * Runs a generic replacement on the tree.
     *
     * @param type      Replacement type option
     * @param inputLine User's formatted input
     */
    void replace(TreeReplaceService.ReplacementType type, InputModel inputLine);
}
