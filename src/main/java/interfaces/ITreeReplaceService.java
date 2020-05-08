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

    /**
     * Runs a generic replacement for tree
     *
     * @param type          Replacement type option
     * @param oldEntityName Old entity name
     * @param newEntityName New entity name
     * @param tree          Input tree
     * @return              Replaced tree
     */
    ISyntaxTree replace(TreeReplaceService.ReplacementType type, String oldEntityName, String newEntityName, ISyntaxTree tree);
}
