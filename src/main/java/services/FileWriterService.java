package services;

import interfaces.IFileWriterService;
import interfaces.ISyntaxTree;

import java.io.File;

/**
 * Helper to write syntax trees to disk
 */
public class FileWriterService implements IFileWriterService {

    /**
     * Write out this syntax tree
     *
     * @param tree      Syntax tree
     * @param filename  Filename to write to
     */
    @Override
    public void write(ISyntaxTree tree, String filename) {
        File file = new File(filename);
        if(file.exists()) {
            file.delete();
        }
        file.mkdirs();
        file.delete();
        tree.write(new File(filename));
    }
}
