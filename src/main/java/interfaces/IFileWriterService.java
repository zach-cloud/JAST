package interfaces;

/**
 * Service to assist writing a tree to a file
 */
public interface IFileWriterService {

    /**
     * Write out this syntax tree
     *
     * @param tree      Syntax tree
     * @param filename  Filename to write to
     */
    void write(ISyntaxTree tree, String filename);
}
