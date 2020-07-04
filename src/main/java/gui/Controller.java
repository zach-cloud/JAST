package gui;

import gui.components.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;

/**
 * Controller for the GUI elements.
 */
public final class Controller {

    private Label statusLabel;
    private CodeArea jassCodeEditor;
    private CodeArea functionBrowser;

    private Scene scene;
    private Stage stage;
    private VBox root;

    private ComponentContext context;
    private KeywordsComponent keywordsComponent;
    private SyntaxHighlighterComponent syntaxHighlighterComponent;
    private AutocompleteComponent autocompleteComponent;
    private ConfigLoaderComponent configLoaderComponent;
    private ThemesComponent themesComponent;
    private FileComponent fileComponent;
    private StatusComponent statusComponent;
    private RawcodeComponent rawcodeComponent;
    private MpqComponent mpqComponent;
    private RefactorComponent refactorComponent;
    private UnhexComponent unhexComponent;
    private SyntaxCheckerComponent syntaxCheckerComponent;
    private StringHashComponent stringHashComponent;
    private CloseComponent closeComponent;
    private SearchComponent searchComponent;
    private HotkeyComponent hotkeyComponent;
    private AboutComponent aboutComponent;

    public void makeComponents() {
        this.context = new ComponentContext(jassCodeEditor, functionBrowser, root, stage, statusLabel);
        this.keywordsComponent = new KeywordsComponent(context);
        this.syntaxHighlighterComponent = new SyntaxHighlighterComponent(context);
        this.autocompleteComponent = new AutocompleteComponent(context);
        this.configLoaderComponent = new ConfigLoaderComponent(context);
        this.themesComponent = new ThemesComponent(context, configLoaderComponent);
        this.statusComponent = new StatusComponent(context);
        this.mpqComponent = new MpqComponent(context, statusComponent, fileComponent);
        this.rawcodeComponent = new RawcodeComponent(context, statusComponent, fileComponent);
        this.fileComponent = new FileComponent(context, statusComponent, configLoaderComponent, rawcodeComponent, mpqComponent);
        this.refactorComponent = new RefactorComponent(context, statusComponent, fileComponent);
        this.unhexComponent = new UnhexComponent(context, statusComponent, refactorComponent);
        this.syntaxCheckerComponent = new SyntaxCheckerComponent(context);
        this.stringHashComponent = new StringHashComponent(context);
        this.closeComponent = new CloseComponent(context, statusComponent);
        this.searchComponent = new SearchComponent(context);
        this.hotkeyComponent = new HotkeyComponent(context);
        this.aboutComponent = new AboutComponent(context);

        this.keywordsComponent.setupKeywords();
        configLoaderComponent.readConfigs(fileComponent);
        setupHotkeys(scene);
        applyDefault();
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }

    public void bindElementSizes() {
        autocompleteComponent.bindElementSizes();
    }

    /**
     * Sets the status label of this controller
     *
     * @param statusLabel Status label
     */
    public void setStatusLabel(Label statusLabel) {
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

    public void setFunctionBrowser(CodeArea functionBrowser) {
        this.functionBrowser = functionBrowser;
    }

    /**
     * Sets up syntax highlighting
     */
    public void setupHighlighting() {
        this.syntaxHighlighterComponent.setupHighlighting();
    }


    /**
     * Prompts the user to open a file
     */
    public void open(ActionEvent e) {
        fileComponent.open();
    }

    /**
     * Prompts the user to save the file
     */
    public void save(ActionEvent e) {
        fileComponent.save();
    }

    /**
     * Closes application
     */
    public void close(ActionEvent e) {
        closeComponent.close();
    }

    /**
     * Adds NZCP to the map.
     */
    public void addNzcp(ActionEvent e) {
        refactorComponent.addNzcp();
    }

    /**
     * Adds JJCP to the map.
     */
    public void addJjcp(ActionEvent e) {
        refactorComponent.addJjcp();
    }

    /**
     * Adds NZCP to the map and deduplicates
     */
    public void addNzcpD(ActionEvent e) {
        refactorComponent.addNzcpD();
    }

    /**
     * Adds JJCP to the map and deduplicates
     */
    public void addJjcpD(ActionEvent e) {
        refactorComponent.addJjcpD();
    }

    /**
     * Merges scripts together without deduplication
     */
    public void mergeScript(ActionEvent e) {
        refactorComponent.mergeScript();
    }

    /**
     * Merges scripts together with deduplication
     */
    public void mergeScriptD(ActionEvent e) {
        refactorComponent.mergeScriptD();
    }

    /**
     * Renames a script variable
     */
    public void renameScriptVariable(ActionEvent e) {
        refactorComponent.renameScriptVariable();
    }

    /**
     * Renames a script function
     */
    public void renameScriptFunction(ActionEvent e) {
        refactorComponent.renameScriptFunction();
    }

    /**
     * Optimizes GUI conditions into a single condition and inlines it
     */
    public void optimizeGui(ActionEvent e) {
        refactorComponent.optimizeGui();
    }

    /**
     * Scrambles all variable and function names
     */
    public void scrambleNames(ActionEvent e) {
        refactorComponent.scrambleNames();
    }

    /**
     * Reformats the code into a readable format
     */
    public void reformatCode(ActionEvent e) {
        refactorComponent.reformatCode();
    }

    /**
     * Minifies/de-formats the code
     */
    public void minifyCode(ActionEvent e) {
        refactorComponent.minifyCode();
    }

    /**
     * Generates rawcodes from an open objects file
     * Or prompts open of an objects file if not selected already
     */
    public void generateRawcodes(ActionEvent e) {
        rawcodeComponent.generateRawcodes();
    }

    /**
     * Extracts MPQ files
     *
     * @param e Ignored
     */
    public void extractMpq(ActionEvent e) {
        mpqComponent.extractMpq();
    }

    public void computeStringhash(ActionEvent e) {
        stringHashComponent.computeStringhash(this);
    }

    public void breakStringhash(ActionEvent e) {
        computeStringhash(e);
    }

    public void calculateStringHash(ActionEvent e) {
        stringHashComponent.calculateStringHash();
    }

    public void breakStringhashExecute(ActionEvent e) {
        stringHashComponent.breakStringhashExecute();
    }

    public void closeStringHash(ActionEvent e) {
        stringHashComponent.closeStringHash();
    }

    public void about(ActionEvent e) {
        aboutComponent.about();
    }

    public void undo(ActionEvent e) {
        jassCodeEditor.undo();
    }

    public void redo(ActionEvent e) {
        jassCodeEditor.redo();
    }

    public void search(ActionEvent e) {
        searchComponent.search(this);
    }

    public void searchExecute(ActionEvent e) {
        searchComponent.searchExecute();
    }

    public void closeSearch(ActionEvent e) {
        searchComponent.closeSearch();
    }

    public void setupHotkeys(Scene scene) {
        hotkeyComponent.setupHotkeys(scene, this);
    }

    public void setupAutocomplete(Scene scene) {
        autocompleteComponent.setupAutocomplete(scene);
    }

    public void syntaxCheck(ActionEvent e) {
        syntaxCheckerComponent.syntaxCheck();
    }

    public void unhex(ActionEvent e) {
        unhexComponent.unhex();
    }

    public void applyDarkTheme(ActionEvent e) {
        themesComponent.applyDarkTheme(scene);
    }

    public void applyJasscraftTheme(ActionEvent e) {
        themesComponent.applyJasscraftTheme(scene);
    }

    public void applyLightTheme(ActionEvent e) {
        themesComponent.applyLightTheme(scene);
    }

    public void showFunctionBrowser(ActionEvent e) {
        autocompleteComponent.showFunctionBrowser();
    }

    public void hideFunctionBrowser(ActionEvent e) {
        autocompleteComponent.hideFunctionBrowser();
    }

    public void applyDefault() {
        themesComponent.applyDefault(scene);
    }

    public void searchForFunction(ActionEvent e) {
        autocompleteComponent.searchForFunction();
    }

    public void clearBrowser(ActionEvent e) {
        autocompleteComponent.clearBrowser();
    }

    public void makeElementsFillScreen(Stage stage, VBox root) {
        configLoaderComponent.makeElementsFillScreen(scene, stage, root);
    }

    public void toggleFunctionBrowser() {
        autocompleteComponent.toggleFunctionBrowser();
    }

    public void runAutocomplete() {
        autocompleteComponent.runAutocomplete();
    }
}
