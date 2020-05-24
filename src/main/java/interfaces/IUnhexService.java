package interfaces;

public interface IUnhexService {

    /**
     * Fixes hex symbols in the script
     *
     * @param tree Syntax tree
     */
    void unhex(ISyntaxTree tree);
}
