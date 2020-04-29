package interfaces;

/**
 * Helper service to load blizzard.j/common.j
 */
public interface IBlizzardLoaderService {

    /**
     * Loads common.j as a Syntax Treee
     *
     * @return  Common.j
     */
    ISyntaxTree loadCommon();

    /**
     * Loads blizzard.j as a Syntax Tree
     *
     * @return  Blizzard.j
     */
    ISyntaxTree loadBlizzard();
}
