package gui.components;

import helper.CheatpackLoader;
import interfaces.IGuiOptimizerService;
import interfaces.ISyntaxTree;
import interfaces.ITreeReplaceService;
import javafx.event.ActionEvent;
import nodes.wts.WtsStringsFile;
import services.GuiOptimizerService;
import services.RandomNameGeneratorService;
import services.TreeReplaceService;
import tree.SyntaxTree;
import tree.TreeContext;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class RefactorComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private FileComponent fileComponent;
    private IGuiOptimizerService optimizer;
    private ITreeReplaceService treeReplaceService;
    
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
     * Adds NZCP to the map.
     */
    public void addNzcp() {
        applyGeneric(false, "NZCP", "easymode");
    }

    /**
     * Adds JJCP to the map.
     */
    public void addJjcp() {
        applyGeneric(false, "JJCP", "wc3edit");
    }

    /**
     * Adds NZCP to the map and deduplicates
     */
    public void addNzcpD() {
        applyGeneric(true, "NZCP", "easymode");
    }

    /**
     * Adds JJCP to the map and deduplicates
     */
    public void addJjcpD() {
        applyGeneric(true, "JJCP", "wc3edit");
    }

    /**
     * Applies a generic cheatpack to the map
     *
     * @param dedupe           True if CP should be deduped
     * @param cpName           Name of the pack
     * @param defaultActivator Default activator of pack
     */
    private void applyGeneric(boolean dedupe, String cpName, String defaultActivator) {
        try {
            String activator = JOptionPane.showInputDialog("Enter custom activator (no dash)");
            ISyntaxTree userTree = SyntaxTree.readTree(context.jassCodeEditor.getText());
            ISyntaxTree cpTree = SyntaxTree.readTree(CheatpackLoader.loadCheatpackByName(cpName));
            cpTree.renameVariable("\"" + defaultActivator + "\"", "\"" + activator + "\"");
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
    public void genericFileMerge(boolean dedupe) {
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
    public void rename(TreeReplaceService.ReplacementType type) {
        long time = System.currentTimeMillis();
        String nameOne = JOptionPane.showInputDialog("Enter name to replace");
        String nameTwo = JOptionPane.showInputDialog("Enter name to replace with");
        ISyntaxTree syntaxTree = SyntaxTree.readTree(context.jassCodeEditor.getText());
        treeReplaceService.replace(type, nameOne, nameTwo, syntaxTree);
        context.jassCodeEditor.replaceText(syntaxTree.toString());
        formatIfDesired();
        time = System.currentTimeMillis() - time;
        statusComponent.changeStatus("Renamed successfully", time);
    }

    /**
     * Renames a script variable
     */
    public void renameScriptVariable() {
        rename(TreeReplaceService.ReplacementType.VARIABLE);
    }

    /**
     * Renames a script function
     */
    public void renameScriptFunction() {
        rename(TreeReplaceService.ReplacementType.FUNCTION);
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
}
