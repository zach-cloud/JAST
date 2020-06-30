package gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import settings.Settings;

public final class GUI extends Application {

    /**
     * Starts application.
     *
     * @param stage Stage to start on.
     */
    @Override
    public void start(Stage stage) {
        Controller controller = new Controller();
        MenuBar menuBar = makeMenus(controller);
        VBox root = new VBox(menuBar);
        setupEditorBox(root, controller);
        Scene scene = setupScene(controller, stage, root);
        controller.setStage(stage);
        controller.setRoot(root);
        controller.makeComponents();
        controller.makeElementsFillScreen(stage, root);
        controller.bindElementSizes();
        stage.show();
        controller.setupHighlighting();
        controller.setupAutocomplete(scene);
    }

    /**
     * Creates menus for user to use app with.
     *
     * @param controller    Controller with menu actions
     * @return              Created menu
     */
    private MenuBar makeMenus(Controller controller) {
        MenuBar menuBar = new MenuBar();
        makeFileMenu(controller, menuBar);
        makeEditMenu(controller, menuBar);
        makeModifyMenu(controller, menuBar);
        makeCodeMenu(controller, menuBar);
        makeUtilityMenu(controller, menuBar);
        makeStyleMenu(controller, menuBar);
        makeBrowserMenu(controller, menuBar);
        return menuBar;
    }

    /**
     * Makes the JASS Code Editor box
     *
     * @param root          VBox to add box to
     * @param controller    Controller with box actions
     * @return              Code editor box
     */
    private CodeArea setupEditorBox(VBox root, Controller controller) {
        final CodeArea jassCodeEditor = new CodeArea();
        final Label statusLabel = new Label("Initialized Successfully (0 ms)");
        final CodeArea functionBrowser = new CodeArea();
        functionBrowser.setEditable(false);
        jassCodeEditor.setWrapText(true);
        jassCodeEditor.setParagraphGraphicFactory(LineNumberFactory.get(jassCodeEditor));

        root.getChildren().add(new VirtualizedScrollPane<>(jassCodeEditor));
        root.getChildren().add(new Label(" "));
        root.getChildren().add(functionBrowser);
        root.getChildren().add(statusLabel);
        controller.setStatusLabel(statusLabel);
        controller.setJassCodeEditor(jassCodeEditor);
        controller.setFunctionBrowser(functionBrowser);
        return jassCodeEditor;
    }

    /**
     * Sets up the scene with default stuff like hotkeys
     *
     * @param controller    Controller with actions
     * @param stage         Stage to set up
     * @param root          Primary application box
     */
    private Scene setupScene(Controller controller, Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setScene(scene);
        stage.setTitle("JASS AST Modifier");
        controller.setupHotkeys(scene);
        controller.applyDefault();
        return scene;
    }

    /**
     * Creates File menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeFileMenu(Controller controller, MenuBar menuBar) {
        Menu fileMenu = new Menu("File");

        MenuItem openButton = new MenuItem("Open");
        MenuItem saveButton = new MenuItem("Save");
        MenuItem exitButton = new MenuItem("Exit");
        MenuItem aboutButton = new MenuItem("About");

        fileMenu.getItems().addAll(openButton, saveButton, exitButton, aboutButton);

        openButton.setOnAction(controller::open);
        saveButton.setOnAction(controller::save);
        exitButton.setOnAction(controller::close);
        aboutButton.setOnAction(controller::about);

        menuBar.getMenus().add(fileMenu);
    }

    /**
     * Creates Utility menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeUtilityMenu(Controller controller, MenuBar menuBar) {
        Menu utilityMenu = new Menu("Utility");

        MenuItem rawcodesButton = new MenuItem("Generate Rawcodes");
        MenuItem extractButton = new MenuItem("Extract MPQ");
        MenuItem stringhashButton = new MenuItem("Compute Stringhash");
        MenuItem breakStringhashButton = new MenuItem("Break Stringhash");

        utilityMenu.getItems().addAll(rawcodesButton, extractButton, stringhashButton, breakStringhashButton);

        rawcodesButton.setOnAction(controller::generateRawcodes);
        extractButton.setOnAction(controller::extractMpq);
        stringhashButton.setOnAction(controller::computeStringhash);
        breakStringhashButton.setOnAction(controller::breakStringhash);

        menuBar.getMenus().add(utilityMenu);
    }

    /**
     * Creates Edit menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeEditMenu(Controller controller, MenuBar menuBar) {
        Menu editMenu = new Menu("Edit");

        MenuItem undoButton = new MenuItem("Undo");
        MenuItem redoButton = new MenuItem("Redo");
        MenuItem searchButton = new MenuItem("Search");

        editMenu.getItems().addAll(undoButton, redoButton, searchButton);

        undoButton.setOnAction(controller::undo);
        redoButton.setOnAction(controller::redo);
        searchButton.setOnAction(controller::search);

        menuBar.getMenus().add(editMenu);
    }

    /**
     * Creates Code menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeCodeMenu(Controller controller, MenuBar menuBar) {
        Menu codeMenu = new Menu("Code");

        MenuItem reformatButton = new MenuItem("Reformat code");
        MenuItem minifyButton = new MenuItem("Minify code");
        MenuItem optimizeGuiButton = new MenuItem("Optimize GUI Triggers");
        MenuItem compileButton = new MenuItem("Compile code");
        MenuItem unhexButton = new MenuItem("Un-hex code");

        codeMenu.getItems().addAll(reformatButton, minifyButton, optimizeGuiButton, compileButton, unhexButton);

        reformatButton.setOnAction(controller::reformatCode);
        minifyButton.setOnAction(controller::minifyCode);
        optimizeGuiButton.setOnAction(controller::optimizeGui);
        compileButton.setOnAction(controller::syntaxCheck);
        unhexButton.setOnAction(controller::unhex);

        menuBar.getMenus().add(codeMenu);
    }

    /**
     * Creates Modify menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeModifyMenu(Controller controller, MenuBar menuBar) {
        Menu modifyMenu = new Menu("Modify");

        MenuItem nzcpButton = new MenuItem("Add NZCP");
        MenuItem jjcpButton = new MenuItem("Add JJCP");
        MenuItem nzcpDedupeButton = new MenuItem("Add NZCP (Deduplicate)");
        MenuItem jjcpDedupeButton = new MenuItem("Add JJCP (Deduplicate)");

        MenuItem mergeButton = new MenuItem("Merge Scripts");
        MenuItem mergeDeduplicateButton = new MenuItem("Merge Scripts (Deduplicate)");
        MenuItem scrambleButton = new MenuItem("Scramble Names");
        MenuItem renameFunctionButton = new MenuItem("Rename Function");
        MenuItem renameVariableButton = new MenuItem("Rename Variable");

        nzcpButton.setOnAction(controller::addNzcp);
        jjcpButton.setOnAction(controller::addJjcp);
        nzcpDedupeButton.setOnAction(controller::addNzcpD);
        jjcpDedupeButton.setOnAction(controller::addJjcpD);

        mergeButton.setOnAction(controller::mergeScript);
        mergeDeduplicateButton.setOnAction(controller::mergeScriptD);
        scrambleButton.setOnAction(controller::scrambleNames);
        renameFunctionButton.setOnAction(controller::renameScriptFunction);
        renameVariableButton.setOnAction(controller::renameScriptVariable);

        if (Settings.CHEATING_ENABLED) {
            modifyMenu.getItems().addAll(nzcpButton, jjcpButton, nzcpDedupeButton, jjcpDedupeButton);
        }
        modifyMenu.getItems().addAll(mergeButton, mergeDeduplicateButton, scrambleButton, renameFunctionButton, renameVariableButton);

        menuBar.getMenus().add(modifyMenu);
    }

    /**
     * Creates Style menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeStyleMenu(Controller controller, MenuBar menuBar) {
        Menu stylesMenu = new Menu("Styles");

        MenuItem darkThemeButton = new MenuItem("Dark Theme");
        MenuItem jasscraftThemeButton = new MenuItem("JASSCraft Theme");
        MenuItem lightThemeButton = new MenuItem("Light Theme");

        darkThemeButton.setOnAction(controller::applyDarkTheme);
        jasscraftThemeButton.setOnAction(controller::applyJasscraftTheme);
        lightThemeButton.setOnAction(controller::applyLightTheme);

        stylesMenu.getItems().addAll(darkThemeButton, jasscraftThemeButton, lightThemeButton);

        menuBar.getMenus().add(stylesMenu);
    }

    /**
     * Creates Function Browser menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeBrowserMenu(Controller controller, MenuBar menuBar) {
        Menu browserMenu = new Menu("Function Browser");

        MenuItem showBrowserButton = new MenuItem("Show Function Browser");
        MenuItem hideBrowserButton = new MenuItem("Hide Function Browser");
        MenuItem searchForFunctionButton = new MenuItem("Search for Function");
        MenuItem clearFunctionBrowserButton = new MenuItem("Clear Function Browser");

        browserMenu.getItems().addAll(showBrowserButton, hideBrowserButton, searchForFunctionButton, clearFunctionBrowserButton);

        showBrowserButton.setOnAction(controller::showFunctionBrowser);
        hideBrowserButton.setOnAction(controller::hideFunctionBrowser);
        searchForFunctionButton.setOnAction(controller::searchForFunction);
        clearFunctionBrowserButton.setOnAction(controller::clearBrowser);

        menuBar.getMenus().add(browserMenu);
    }

    /**
     * Main entry point.
     *
     * @param args  Ignored
     */
    public void main(String[] args) {
        launch(args);
    }
}
