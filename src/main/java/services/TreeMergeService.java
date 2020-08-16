package services;

import template.TemplateLoader;
import interfaces.IFileWriterService;
import interfaces.IOutputService;
import interfaces.ISyntaxTree;
import interfaces.ITreeMergeService;
import model.InputModel;
import tree.SyntaxTree;

/**
 * Helper service for merging code files.
 */
public class TreeMergeService implements ITreeMergeService {
    
    private IOutputService outputService;
    private IFileWriterService writerService;

    /**
     * Creates a new Merge Helper
     *
     * @param outputService Service to display output
     */
    public TreeMergeService(IOutputService outputService, IFileWriterService writerService) {
        this.outputService = outputService;
        this.writerService = writerService;
    }

    /**
     * Merges code files together based on input.
     *
     * @param inputModel User's formatted input
     * @param dedupe     True if code should be de-duped before inserting
     */
    @Override
    public void merge(InputModel inputModel, boolean dedupe) {
        merge(dedupe, inputModel.getTree1(), inputModel.getTree2(), inputModel.getOutputPath());
    }


    /**
     * Merges code files together based on input.
     *
     * @param dedupe    True if code should be de-duped before inserting
     * @param tree1     First syntax tree to merge
     * @param tree2     Second syntax tree to merge
     * @param outputFile Output file path
     */
    public void merge(boolean dedupe, ISyntaxTree tree1, ISyntaxTree tree2, String outputFile) {
        if(dedupe) {
            outputService.print("De-duplicating variables/functions");
            tree2.deduplicate(new RandomNameGeneratorService());
            outputService.print("Completed variable/function deduplication");
        }
        tree1.merge(tree2);
        outputService.print("Merged into " + tree1.getScript().getGlobalsSection().getGlobalVariables().size() + " variables and " + tree1.getScript().getFunctionsSection().getFunctions().size() + " functions.");
        writerService.write(tree1, outputFile);
        outputService.print("Saved script to: " + outputFile);
    }

    /**
     * Applies a generic cheatpack to the script
     *
     * @param dedupe            True if code should be de-duped before inserting
     * @param cheatpackName     Name of cheatpack to insert
     * @param defaultActivator  The default activator of the cheatpack
     * @param input             User's formatted input
     */
    public void applyCp(boolean dedupe, String cheatpackName, String defaultActivator, InputModel input) {
        String activator = input.getActivator().replace("\\s", " ");
        input.getTree2().renameVariable("\"" + defaultActivator + "\"", "\"" + activator + "\"");
        merge(dedupe, input.getTree1(), input.getTree2(), input.getOutputPath());
    }

    /**
     * Adds NZCP to a map.
     *
     * @param dedupe            True if code should be de-duped before inserting
     * @param input             User's formatted input
     */
    public void applyNzcp(boolean dedupe, InputModel input) {
        input.setTree2(SyntaxTree.readTree(TemplateLoader.loadTemplateByName("NZCP.j")));
        applyCp(dedupe, "NZCP.j", "easymode", input);
    }

    /**
     * Adds JJCP to a map.
     *
     * @param dedupe            True if code should be de-duped before inserting
     * @param input             User's formatted input
     */
    public void applyJjcp(boolean dedupe, InputModel input) {
        input.setTree2(SyntaxTree.readTree(TemplateLoader.loadTemplateByName("JJCP.j")));
        applyCp(dedupe, "JJCP.j", "wc3edit", input);
    }
}
