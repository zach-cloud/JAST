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

    /**
     * Writes out this String to a file
     *
     * @param output    Output String
     * @param filename  Filename to write to
     */
    void writeString(String output, String filename);
}
