package gui.components;

import gui.Controller;
import gui.window.MergeCommandWindow;
import gui.window.ReplaceCommandWindow;
import helper.CheatpackLoader;
import interfaces.IGuiOptimizerService;
import interfaces.ISyntaxTree;
import interfaces.ITreeReplaceService;
import nodes.wts.WtsStringsFile;
import services.GuiOptimizerService;
import services.RandomNameGeneratorService;
import services.TreeReplaceService;
import tree.SyntaxTree;
import tree.TreeContext;

import java.io.File;
import java.util.Scanner;

public final class RefactorComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private FileComponent fileComponent;
    private IGuiOptimizerService optimizer;
    private ITreeReplaceService treeReplaceService;
    private ReplaceCommandWindow replaceWindow;
    private MergeCommandWindow mergeWindow;
    
    public RefactorComponent(ComponentContext context, StatusComponent statusComponent, FileComponent fileComponent) {
        super(context);
        this.statusComponent = statusComponent;
        this.fileComponent = fileComponent;
        this.optimizer = new GuiOptimizerService();
        this.treeReplaceService = new TreeReplaceService();
    }

    public void reformatCode() {
        context.formattingDesired = true;
        try {
            if (context.openType == null || context.openType == ComponentContext.OpenType.SCRIPT || context.openType == ComponentContext.OpenType.TEXTFILE) {
                long time = System.currentTimeMillis();
                statusComponent.changeStatus("Reading Syntax Tree");
                ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
                statusComponent.changeStatus("Writing tree");
                context.jassCodeEditor.replaceText(tree.getFormatted());
                time = System.currentTimeMillis() - time;
                statusComponent.changeStatus("Formatted JASS code", time);
            } else if (context.openType == ComponentContext.OpenType.STRINGS) {
                long time = System.currentTimeMillis();
                statusComponent.changeStatus("Reading WTS Tree");
                WtsStringsFile stringsFile = new WtsStringsFile(new Scanner(context.jassCodeEditor.getText()), new TreeContext());
                statusComponent.changeStatus("Writing WTS Tree");
                context.jassCodeEditor.replaceText(stringsFile.toFormattedString(0));
                time = System.currentTimeMillis() - time;
                statusComponent.changeStatus("Formatted WTS code", time);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus(ex.getMessage());
        }
    }

    public void minifyCode() {
        context.formattingDesired = false;
        try {
            if (context.openType == null || context.openType == ComponentContext.OpenType.SCRIPT || context.openType == ComponentContext.OpenType.TEXTFILE) {
                long time = System.currentTimeMillis();
                statusComponent.changeStatus("Reading Syntax Tree");
                ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
                statusComponent.changeStatus("Writing tree");
                context.jassCodeEditor.replaceText(tree.getString());
                time = System.currentTimeMillis() - time;
                statusComponent.changeStatus("Wrote JASS code", time);
            } else if (context.openType == ComponentContext.OpenType.STRINGS) {
                long time = System.currentTimeMillis();
                statusComponent.changeStatus("Reading WTS Tree");
                WtsStringsFile stringsFile = new WtsStringsFile(new Scanner(context.jassCodeEditor.getText()), new TreeContext());
                statusComponent.changeStatus("Writing WTS Tree");
                context.jassCodeEditor.replaceText(stringsFile.toString());
                time = System.currentTimeMillis() - time;
                statusComponent.changeStatus("Wrote WTS code", time);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus(ex.getMessage());
        }
    }

    /**
     * Merges code files together based on input.
     *
     * @param dedupe True if code should be de-duped before inserting
     * @param tree1  First syntax tree to merge
     * @param tree2  Second syntax tree to merge
     */
    public ISyntaxTree merge(boolean dedupe, ISyntaxTree tree1, ISyntaxTree tree2) {
        if (dedupe) {
            statusComponent.changeStatus("De-duplicating variables/functions");
            tree2.deduplicate(new RandomNameGeneratorService());
            statusComponent.changeStatus("Completed variable/function deduplication");
        }
        tree1.merge(tree2);
        statusComponent.changeStatus("Merged into " + tree1.getScript().getGlobalsSection().getGlobalVariables().size() + " variables and " + tree1.getScript().getFunctionsSection().getFunctions().size() + " functions.");
        context.jassCodeEditor.replaceText(tree1.getString());
        formatIfDesired();
        return tree1;
    }

    /**
     * Applies a generic cheatpack to the map
     *
     * @param dedupe           True if CP should be deduped
     * @param cpName           Name of the pack
     * @param defaultActivator Default activator of pack
     */
    private void applyGeneric(boolean dedupe, String cpName, String defaultActivator, String customActivator) {
        try {
            ISyntaxTree userTree = SyntaxTree.readTree(context.jassCodeEditor.getText());
            ISyntaxTree cpTree = SyntaxTree.readTree(CheatpackLoader.loadCheatpackByName(cpName));
            cpTree.renameVariable("\"" + defaultActivator + "\"", "\"" + customActivator + "\"");
            context.jassCodeEditor.replaceText(merge(dedupe, userTree, cpTree).getString());
            formatIfDesired();
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to parse tree.");
        }
    }

    /**
     * Merges code files together
     *
     * @param dedupe True if code should be deduped, false if not
     */
    private void genericFileMerge(boolean dedupe) {
        try {
            long time = System.currentTimeMillis();
            statusComponent.changeStatus("Prompting file open");
            File selectedFile = fileComponent.getFile();
            if (selectedFile != null) {
                ISyntaxTree tree1 = SyntaxTree.readTree(context.jassCodeEditor.getText());
                ISyntaxTree tree2 = SyntaxTree.readTree(selectedFile);
                context.jassCodeEditor.replaceText(merge(dedupe, tree1, tree2).getString());
                formatIfDesired();
            }
            time = System.currentTimeMillis() - time;
            statusComponent.changeStatus("Done with merging", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to parse file");
        }
    }

    /**
     * Merges scripts together without deduplication
     */
    public void mergeScript() {
        genericFileMerge(false);
    }

    /**
     * Merges scripts together with deduplication
     */
    public void mergeScriptD() {
        genericFileMerge(true);
    }

    /**
     * Renames a script entity generically
     *
     * @param type Rename type to apply
     */
    private void rename(TreeReplaceService.ReplacementType type, String nameOne, String nameTwo) {
        long time = System.currentTimeMillis();
        ISyntaxTree syntaxTree = SyntaxTree.readTree(context.jassCodeEditor.getText());
        treeReplaceService.replace(type, nameOne, nameTwo, syntaxTree);
        context.jassCodeEditor.replaceText(syntaxTree.toString());
        formatIfDesired();
        time = System.currentTimeMillis() - time;
        statusComponent.changeStatus("Renamed successfully", time);
    }

    /**
     * Optimizes GUI conditions into a single condition and inlines it
     */
    public void optimizeGui() {
        try {
            long time = System.currentTimeMillis();
            statusComponent.changeStatus("Reading Syntax Tree");
            ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
            statusComponent.changeStatus("Writing tree");
            String newData = optimizer.optimize(tree).getString();
            context.jassCodeEditor.replaceText(newData);
            formatIfDesired();
            time = System.currentTimeMillis() - time;
            statusComponent.changeStatus("Optimized GUI", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to parse tree.");
        }
    }

    /**
     * Scrambles all variable and function names
     */
    public void scrambleNames() {
        try {
            long time = System.currentTimeMillis();
            statusComponent.changeStatus("Reading Syntax Tree");
            ISyntaxTree tree = SyntaxTree.readTree(context.jassCodeEditor.getText());
            statusComponent.changeStatus("Writing tree");
            tree.deduplicate(new RandomNameGeneratorService());
            String newData = tree.getString();
            context.jassCodeEditor.replaceText(newData);
            formatIfDesired();
            time = System.currentTimeMillis() - time;
            statusComponent.changeStatus("Scrambled names", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to parse tree.");
        }
    }

    /**
     * If formatting is set to true, will format the code. else does nothing.
     */
    public void formatIfDesired() {
        if (context.formattingDesired) {
            reformatCode();
        }
    }

    public void runRename() {
        if(replaceWindow != null) {
            String type = replaceWindow.getSelectionType();
            String oldName = replaceWindow.getOriginal();
            String newName = replaceWindow.getReplaceWIth();
            if(type.equalsIgnoreCase("global variable")) {
                rename(TreeReplaceService.ReplacementType.VARIABLE, oldName, newName);
            } else if(type.equalsIgnoreCase("function")) {
                rename(TreeReplaceService.ReplacementType.VARIABLE, oldName, newName);
            } else {
                throw new RuntimeException("Illegal option: " + type);
            }
        }
    }

    public void closeRename() {
        if(replaceWindow != null) {
            replaceWindow.hide();
        }
    }

    public void rename(Controller controller) {
        if (replaceWindow == null) {
            replaceWindow = new ReplaceCommandWindow(controller);
        }
        replaceWindow.show();
    }

    public void openMerge(Controller controller) {
        if (mergeWindow == null) {
            mergeWindow = new MergeCommandWindow(controller);
        }
        mergeWindow.show();
    }

    public void closeMerge() {
        if(mergeWindow != null) {
            mergeWindow.hide();
        }
    }

    public void runMerge() {
        if(mergeWindow != null) {
            String mergeType = mergeWindow.getMergeType();
            String activator = mergeWindow.getActivator();
            boolean dedupe = mergeWindow.dedupe();
            if(mergeType.equalsIgnoreCase("jjcp")) {
                applyGeneric(dedupe, mergeType.toUpperCase(), "wc3edit", activator);
            } else if(mergeType.equalsIgnoreCase("nzcp")) {
                applyGeneric(dedupe, mergeType.toUpperCase(), "easymode", activator);
            } else if(mergeType.equalsIgnoreCase("select file...")) {
                genericFileMerge(dedupe);
            } else {
                throw new RuntimeException("Illegal option: " + mergeType);
            }
        }
    }

}
