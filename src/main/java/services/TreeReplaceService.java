package services;

import interfaces.IFileWriterService;
import interfaces.IOutputService;
import interfaces.ISyntaxTree;
import interfaces.ITreeReplaceService;
import model.InputModel;
import tree.SyntaxTree;

import java.io.File;

/**
 * Helper service for replacing values from a syntax tree
 */
public class TreeReplaceService implements ITreeReplaceService {
    
    private IOutputService outputService;
    private IFileWriterService writerService;

    /**
     * Creates a new Replacement Helper
     *
     * @param outputService Service to display output
     */
    public TreeReplaceService(IOutputService outputService, IFileWriterService writerService) {
        this.outputService = outputService;
        this.writerService = writerService;
    }

    /**
     * The supported types of rename statements
     */
    public enum ReplacementType {
        VARIABLE,
        FUNCTION,
        STRING
    }

    /**
     * Runs a generic replacement on the tree.
     *
     * @param type      Replacement type option
     * @param inputLine User's formatted input
     */
    @Override
    public void replace(ReplacementType type, InputModel inputLine) {
        outputService.print("Running rename command");
        ISyntaxTree tree = inputLine.getTree1();
        String oldEntityName = inputLine.getOldName();
        String newEntityName = inputLine.getNewName();
        ISyntaxTree outputTree = null;
        if(type == ReplacementType.VARIABLE) {
            outputTree = tree.renameVariable(oldEntityName, newEntityName);
        } else if(type == ReplacementType.FUNCTION) {
            outputTree = tree.renameFunction(oldEntityName, newEntityName);
        } else if(type == ReplacementType.STRING) {
            outputTree = tree.renameVariable("\"" + oldEntityName + "\"", "\"" + newEntityName + "\"");
        }
        writerService.write(outputTree, inputLine.getOutputPath());
        outputService.print("Rename completed successfully");
    }
}
