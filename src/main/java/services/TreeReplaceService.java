package services;

import interfaces.IFileWriterService;
import interfaces.IOutputService;
import interfaces.ISyntaxTree;
import interfaces.ITreeReplaceService;
import model.InputModel;

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

    public TreeReplaceService() {
        this.outputService = new OutputService();
        this.writerService = new FileWriterService();
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
        replace(type, oldEntityName, newEntityName, tree);
        writerService.write(tree, inputLine.getOutputPath());
        outputService.print("Rename completed successfully");
    }

    /**
     * Runs a generic replacement for tree
     *
     * @param type          Replacement type option
     * @param oldEntityName Old entity name
     * @param newEntityName New entity name
     * @param tree          Input tree
     */
    public void replace(ReplacementType type, String oldEntityName, String newEntityName, ISyntaxTree tree) {
        if(type == ReplacementType.VARIABLE) {
            tree.renameVariable(oldEntityName, newEntityName);
        } else if(type == ReplacementType.FUNCTION) {
            tree.renameFunction(oldEntityName, newEntityName);
        } else if(type == ReplacementType.STRING) {
            tree.renameVariable("\"" + oldEntityName + "\"", "\"" + newEntityName + "\"");
        }
    }
}
