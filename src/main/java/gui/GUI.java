package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

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
        TreeItem<String> solutionExplorerRoot = new TreeItem<>("Solution Explorer");
        TreeView<String> solutionExplorerView = new TreeView<>(solutionExplorerRoot);

        solutionExplorerView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<TreeItem<String>>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends TreeItem<String>> observable,
                            TreeItem<String> old_val, TreeItem<String> new_val) {
                        TreeItem<String> selectedItem = new_val;
                        controller.openSolutionFile(selectedItem);
                    }
                });

        AnchorPane root = new AnchorPane();
        VBox editorRoot = new VBox();
        VBox solutionExplorerBox = new VBox();
        solutionExplorerBox.getChildren().add(solutionExplorerView);
        final Label statusLabel = new Label("Initialized Successfully (0 ms)");

        AnchorPane.setTopAnchor(menuBar, 0.);
        AnchorPane.setTopAnchor(editorRoot, 25.);
        AnchorPane.setBottomAnchor(editorRoot, 20.);
        AnchorPane.setRightAnchor(editorRoot, 0.);
        AnchorPane.setLeftAnchor(editorRoot, 270.);
        AnchorPane.setTopAnchor(solutionExplorerBox, 25.);
        AnchorPane.setBottomAnchor(solutionExplorerView, 0.);
        AnchorPane.setBottomAnchor(statusLabel, 0.);

        solutionExplorerView.setMaxWidth(270);
        solutionExplorerView.setMinWidth(270);
        solutionExplorerView.minHeightProperty().bind(editorRoot.heightProperty().subtract(17));
        solutionExplorerView.maxHeightProperty().bind(editorRoot.heightProperty().subtract(17));

        root.getChildren().addAll(solutionExplorerBox, editorRoot, menuBar, statusLabel);

        setupEditorBox(root, editorRoot, controller);
        Scene scene = setupScene(controller, stage, root);
        controller.setStage(stage);
        controller.setStatusLabel(statusLabel);
        controller.setRoot(editorRoot);
        controller.setSolutionExplorerRoot(solutionExplorerRoot);
        controller.setSolutionExplorerView(solutionExplorerView);
        controller.makeComponents();
        controller.makeElementsFillScreen(stage, editorRoot);
        controller.bindElementSizes();
        controller.findProjects();
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
        makeAnalysisMenu(controller, menuBar);
        return menuBar;
    }

    /**
     * Makes the JASS Code Editor box
     *
     * @param root          VBox to add box to
     * @param controller    Controller with box actions
     * @return              Code editor box
     */
    private CodeArea setupEditorBox(Pane root, Pane editorRoot, Controller controller) {
        final CodeArea jassCodeEditor = new CodeArea();
        final CodeArea functionBrowser = new CodeArea();
        functionBrowser.setEditable(false);
        jassCodeEditor.setWrapText(true);
        jassCodeEditor.setParagraphGraphicFactory(LineNumberFactory.get(jassCodeEditor));

        editorRoot.getChildren().add(new VirtualizedScrollPane<>(jassCodeEditor));
        editorRoot.getChildren().add(new Label(" "));
        editorRoot.getChildren().add(functionBrowser);
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
    private Scene setupScene(Controller controller, Stage stage, Pane root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller.setScene(scene);
        stage.setTitle("JASS AST Modifier");
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
        MenuItem saveAsButton = new MenuItem("Save As Text...");
        MenuItem mpqSaveButton = new MenuItem("Save As MPQ...");
        MenuItem closeProjectButton = new MenuItem("Close Project");
        MenuItem exitButton = new MenuItem("Exit");
        MenuItem aboutButton = new MenuItem("About");

        fileMenu.getItems().addAll(openButton, saveAsButton, mpqSaveButton, closeProjectButton, exitButton, aboutButton);

        openButton.setOnAction(controller::open);
        saveButton.setOnAction(controller::save);
        saveAsButton.setOnAction(controller::saveAs);
        mpqSaveButton.setOnAction(controller::mpqSave);
        closeProjectButton.setOnAction(controller::closeProject);
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

        MenuItem stringhashButton = new MenuItem("Compute Stringhash");
        MenuItem breakStringhashButton = new MenuItem("Break Stringhash");

        utilityMenu.getItems().addAll(stringhashButton, breakStringhashButton);

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

        MenuItem mergeButton = new MenuItem("Merge Scripts");
        MenuItem scrambleButton = new MenuItem("Scramble Names");
        MenuItem renameButton = new MenuItem("Rename Variable/Function");

        mergeButton.setOnAction(controller::openMerge);
        scrambleButton.setOnAction(controller::scrambleNames);
        renameButton.setOnAction(controller::renameScriptVariable);

        modifyMenu.getItems().addAll(mergeButton, scrambleButton, renameButton);

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
     * Creates Analysis menu
     *
     * @param controller    Controller with actions
     * @param menuBar       Menu bar to add to
     */
    private void makeAnalysisMenu(Controller controller, MenuBar menuBar) {
        Menu analysisMenu = new Menu("Analysis");

        MenuItem isolateButton = new MenuItem("Isolate");
        MenuItem scopeReportButton = new MenuItem("Scope Report");
//        MenuItem findRepeatedCodeButton = new MenuItem("Find repeated code");
//        MenuItem detectMemoryLeaks = new MenuItem("Detect Memory Leaks");

        analysisMenu.getItems().addAll(isolateButton, scopeReportButton);//, findRepeatedCodeButton, detectMemoryLeaks);

        isolateButton.setOnAction(controller::isolate);
        scopeReportButton.setOnAction(controller::scopeReport);

        menuBar.getMenus().add(analysisMenu);
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
