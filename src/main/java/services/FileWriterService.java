package services;

import interfaces.IFileWriterService;
import interfaces.ISyntaxTree;

import java.io.File;
import java.io.PrintWriter;

/**
 * Helper to write syntax trees to disk
 */
public class FileWriterService implements IFileWriterService {

    /**
     * Writes out this String to a file
     *
     * @param output   Output String
     * @param filename Filename to write to
     */
    @Override
    public void writeString(String output, String filename) {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.println(output);
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            throw new RuntimeException("Could not write to file: " + filename);
        }
    }

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
