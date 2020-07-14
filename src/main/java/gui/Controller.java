package gui;

import gui.window.CompileResultsWindow;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import tree.SyntaxTree;

/**
 * Controller for the GUI elements.
 */
public final class Controller {

    private Label statusLabel;
    private CodeArea jassCodeEditor;
    private CodeArea functionBrowser;

    private Scene scene;
    private Stage stage;
    private Pane root;
    private TreeView<String> solutionExplorerView;
    private TreeItem<String> solutionExplorerRoot;

    private Components components;

    /**
     * Generates the needed components to use this controller's methods
     * Can't be invoked on the constructor due to dependencies...
     * TODO fix that.
     */
    void makeComponents() {
        this.components = new Components(jassCodeEditor, functionBrowser, root, stage, statusLabel, solutionExplorerView, solutionExplorerRoot);
        setupHotkeys(scene);
        applyDefault();
    }

    /**
     * Sets available program scene
     *
     * @param scene Program scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Sets the primary program stage
     *
     * @param stage Program stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets available root of the frame.
     *
     * @param root  Vbox root
     */
    public void setRoot(Pane root) {
        this.root = root;
    }

    /**
     * Formats the GUI elements.
     */
    void bindElementSizes() {
        components.autocompleteComponent.bindElementSizes();
    }

    /**
     * Sets the status label of this controller
     *
     * @param statusLabel Status label
     */
    void setStatusLabel(Label statusLabel) {
        this.statusLabel = statusLabel;
    }

    /**
     * Sets the available jass code editor
     *
     * @param jassCodeEditor The jass code editor
     */
    public void setJassCodeEditor(CodeArea jassCodeEditor) {
        this.jassCodeEditor = jassCodeEditor;
    }

    /**
     * Sets the available function browser
     *
     * @param functionBrowser   The function browser box
     */
    void setFunctionBrowser(CodeArea functionBrowser) {
        this.functionBrowser = functionBrowser;
    }

    /**
     * Sets up syntax highlighting
     */
    void setupHighlighting() {
        components.syntaxHighlighterComponent.setupHighlighting();
    }

    /**
     * Prompts the user to open a file
     *
     * @param e Ignored
     */
    public void open(ActionEvent e) {
        components.fileComponent.open();
    }

    /**
     * Prompts the user to save the file
     *
     * @param e Ignored
     */
    public void save(ActionEvent e) {
        components.fileComponent.save();
    }

    /**
     * Closes application
     *
     * @param e Ignored
     */
    public void close(ActionEvent e) {
        components.closeComponent.close();
    }

    /**
     * Renames a script variable
     *
     * @param e Ignored
     */
    void renameScriptVariable(ActionEvent e) {
        components.refactorComponent.rename(this);
    }

    /**
     * Optimizes GUI conditions into a single condition and inlines it
     *
     * @param e Ignored
     */
    void optimizeGui(ActionEvent e) {
        components.refactorComponent.optimizeGui();
    }

    /**
     * Scrambles all variable and function names
     *
     * @param e Ignored
     */
    void scrambleNames(ActionEvent e) {
        components.refactorComponent.scrambleNames();
    }

    /**
     * Reformats the code into a readable format
     *
     * @param e Ignored
     */
    public void reformatCode(ActionEvent e) {
        components.refactorComponent.reformatCode();
    }

    /**
     * Minifies/de-formats the code
     *
     * @param e Ignored
     */
    void minifyCode(ActionEvent e) {
        components.refactorComponent.minifyCode();
    }

    /**
     * Generates rawcodes from an open objects file
     * Or prompts open of an objects file if not selected already
     *
     * @param e Ignored
     */
    void generateRawcodes(ActionEvent e) {
        components.rawcodeComponent.generateRawcodes();
    }

    /**
     * Extracts MPQ files
     *
     * @param e Ignored
     */
    void extractMpq(ActionEvent e) {
        components.mpqComponent.extractMpq();
    }

    /**
     * Opens a window to compute String Hash
     *
     * @param e Ignored
     */
    void computeStringhash(ActionEvent e) {
        components.stringHashComponent.computeStringhash(this);
    }

    /**
     * Opens a window to break String Hash
     *
     * @param e Ignored
     */
    void breakStringhash(ActionEvent e) {
        computeStringhash(e);
    }

    /**
     * Calculates current String Hash value
     *
     * @param e Ignored
     */
    public void calculateStringHash(ActionEvent e) {
        components.stringHashComponent.calculateStringHash();
    }

    /**
     * Breaks current String Hash value
     *
     * @param e Ignored
     */
    public void breakStringhashExecute(ActionEvent e) {
        components.stringHashComponent.breakStringhashExecute();
    }

    /**
     * Closes String hash window
     *
     * @param e Ignored
     */
    public void closeStringHash(ActionEvent e) {
        components.stringHashComponent.closeStringHash();
    }

    /**
     * Shows About popup
     *
     * @param e Ignored
     */
    public void about(ActionEvent e) {
        components.aboutComponent.about();
    }

    /**
     * Undoes last operation
     *
     * @param e Ignored
     */
    public void undo(ActionEvent e) {
        jassCodeEditor.undo();
    }

    /**
     * Redoes last undone operation
     *
     * @param e Ignored
     */
    public void redo(ActionEvent e) {
        jassCodeEditor.redo();
    }

    /**
     * Opens the search window
     *
     * @param e Ignored
     */
    public void search(ActionEvent e) {
        components.searchComponent.search(this);
    }

    /**
     * Executes search on the current input
     *
     * @param e Ignored
     */
    public void searchExecute(ActionEvent e) {
        components.searchComponent.searchExecute();
    }

    /**
     * Closes search window
     *
     * @param e Ignored
     */
    public void closeSearch(ActionEvent e) {
        components.searchComponent.closeSearch();
    }

    /**
     * Sets up program hotkeys
     *
     * @param scene Scene of application
     */
    void setupHotkeys(Scene scene) {
        components.hotkeyComponent.setupHotkeys(scene, this);
    }

    /**
     * Sets up autocomplete for jass keywords
     *
     * @param scene Scene of application
     */
    void setupAutocomplete(Scene scene) {
        components.autocompleteComponent.setupAutocomplete();
    }

    /**
     * Compiles code using jasshelper
     *
     * @param e Ignored
     */
    public void syntaxCheck(ActionEvent e) {
        components.syntaxCheckerComponent.syntaxCheck();
    }

    /**
     * Fixes hex errors in code
     *
     * @param e Ignored
     */
    void unhex(ActionEvent e) {
        components.unhexComponent.unhex();
    }

    /**
     * Shows dark theme
     *
     * @param e Ignored
     */
    void applyDarkTheme(ActionEvent e) {
        components.themesComponent.applyDarkTheme(scene);
    }

    /**
     * Shows jasscraft theme
     *
     * @param e Ignored
     */
    void applyJasscraftTheme(ActionEvent e) {
        components.themesComponent.applyJasscraftTheme(scene);
    }

    /**
     * Shows light theme
     *
     * @param e Ignored
     */
    void applyLightTheme(ActionEvent e) {
        components.themesComponent.applyLightTheme(scene);
    }

    /**
     * Shows function browser text area
     *
     * @param e Ignored
     */
    void showFunctionBrowser(ActionEvent e) {
        components.autocompleteComponent.showFunctionBrowser();
    }

    /**
     * Hides function browser text area
     *
     * @param e Ignored
     */
    void hideFunctionBrowser(ActionEvent e) {
        components.autocompleteComponent.hideFunctionBrowser();
    }

    /**
     * Applies default theme of app
     */
    private void applyDefault() {
        components.themesComponent.applyDefault(scene);
    }

    /**
     * Shows search popup for function browser
     *
     * @param e Ignored
     */
    void searchForFunction(ActionEvent e) {
        components.autocompleteComponent.searchForFunction();
    }

    /**
     * Clears current function browser results
     *
     * @param e Ignored
     */
    void clearBrowser(ActionEvent e) {
        components.autocompleteComponent.clearBrowser();
    }

    /**
     * Makes the code area/function browser fit the screen
     *
     * @param stage Stage of app
     * @param root  Main vbox of app
     */
    void makeElementsFillScreen(Stage stage, Pane root) {
        components.configLoaderComponent.makeElementsFillScreen(scene, stage, root);
    }

    /**
     * Shows/hides function browser
     */
    public void toggleFunctionBrowser() {
        components.autocompleteComponent.toggleFunctionBrowser();
    }

    /**
     * Runs autocomplete on the user's current word
     */
    public void runAutocomplete() {
        components.autocompleteComponent.runAutocomplete();
    }

    /**
     * Opens isolate window.
     *
     * @param e Ignored
     */
    public void isolate(ActionEvent e) {
        components.isolateComponent.isolate(this);
    }

    /**
     * Runs variable/function replace
     *
     * @param e Ignored
     */
    public void runReplace(ActionEvent e) {
        components.refactorComponent.runRename();
    }

    /**
     * Closes replace window
     *
     * @param e Ignored
     */
    public void closeReplace(ActionEvent e) {
        components.refactorComponent.closeRename();
    }

    /**
     * Opens merge window
     *
     * @param e Ignored
     */
    public void openMerge(ActionEvent e) {
        components.refactorComponent.openMerge(this);
    }

    /**
     * Runs a merge command.
     *
     * @param e Ignored
     */
    public void runMerge(ActionEvent e) {
        components.refactorComponent.runMerge();
    }

    /**
     * Closes merge window.
     *
     * @param e Ignored
     */
    public void closeMerge(ActionEvent e) {
        components.refactorComponent.closeMerge();
    }

    /**
     * Isolates a variable or function.
     *
     * @param actionEvent   Ignored
     */
    public void runIsolate(ActionEvent actionEvent) {
        components.isolateComponent.runIsolate();
    }

    /**
     * Closes isolate window.
     *
     * @param actionEvent   Ignored
     */
    public void closeIsolate(ActionEvent actionEvent) {
        components.isolateComponent.close();
    }

    /**
     * Runs scope report.
     *
     * @param e Ignored
     */
    public void scopeReport(ActionEvent e) {
        components.isolateComponent.runScopeReport();
    }

    public void setSolutionExplorerView(TreeView<String> solutionExplorerView) {
        this.solutionExplorerView = solutionExplorerView;
    }

    public void setSolutionExplorerRoot(TreeItem<String> solutionExplorerRoot) {
        this.solutionExplorerRoot = solutionExplorerRoot;
    }
}
