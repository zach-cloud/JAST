package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        Controller controller = new Controller();
        MenuBar menuBar = makeMenus(controller);
        VBox root = new VBox(menuBar);
        CodeArea jassCodeEditor = setupEditorBox(root, controller);
        setupScene(stage, root);
        makeElementsFillScreen(stage, root);
        bindElementSizes(stage, root, jassCodeEditor);
        stage.show();
        setBackgroundColor(jassCodeEditor);
        controller.setupHighlighting();
    }

    private MenuBar makeMenus(Controller controller) {
        MenuBar menuBar = new MenuBar();
        makeFileMenu(controller, menuBar);
        makeEditMenu(controller, menuBar);
        makeCodeMenu(controller, menuBar);
        return menuBar;
    }

    private CodeArea setupEditorBox(VBox root, Controller controller) {
        final CodeArea jassCodeEditor = new CodeArea();
        final Label statusLabel = new Label("Initialized Successfully (0 ms)");
        jassCodeEditor.setWrapText(true);
        jassCodeEditor.setStyle("-fx-text-fill: white;");
        jassCodeEditor.setParagraphGraphicFactory(LineNumberFactory.get(jassCodeEditor));

        root.getChildren().add(new VirtualizedScrollPane<>(jassCodeEditor));
        root.getChildren().add(statusLabel);
        controller.setStatusLabel(statusLabel);
        controller.setJassCodeEditor(jassCodeEditor);
        return jassCodeEditor;
    }

    private void setupScene(Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(GUI.class.getResource("jass-keywords.css").toExternalForm());
        stage.setTitle("JASS AST Modifier");
    }

    private void bindElementSizes(Stage stage, VBox root, CodeArea jassCodeEditor) {
        root.prefHeightProperty().bind(stage.heightProperty());
        jassCodeEditor.prefHeightProperty().bind(root.heightProperty());
    }

    private void setBackgroundColor(CodeArea jassCodeEditor) {
        //jassCodeEditor.setBackground(new Background(new BackgroundFill(Paint.valueOf("black"), null, null)));
        //Region region = ( Region ) jassCodeEditor.lookup( ".content" );
        //region.setBackground( new Background( new BackgroundFill( Color.rgb(43,43, 43), CornerRadii.EMPTY, Insets.EMPTY ) ) );
    }

    private void makeElementsFillScreen(Stage stage, VBox root) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        root.setMinWidth(primaryScreenBounds.getWidth());
        root.setMinHeight(primaryScreenBounds.getHeight());
    }

    private void makeFileMenu(Controller controller, MenuBar menuBar) {
        Menu fileMenu = new Menu("File");

        MenuItem fileMenuButton1 = new MenuItem("Open");
        MenuItem fileMenuButton2 = new MenuItem("Save");
        MenuItem fileMenuButton3 = new MenuItem("Exit");

        fileMenuButton1.setOnAction(controller::open);
        fileMenuButton2.setOnAction(controller::save);
        fileMenuButton3.setOnAction(controller::close);

        fileMenu.getItems().add(fileMenuButton1);
        fileMenu.getItems().add(fileMenuButton2);
        fileMenu.getItems().add(fileMenuButton3);

        menuBar.getMenus().add(fileMenu);
    }

    private void makeCodeMenu(Controller controller, MenuBar menuBar) {
        Menu codeMenu = new Menu("Code");

        MenuItem codeButton1 = new MenuItem("Reformat code");
        MenuItem codeButton2 = new MenuItem("De-format code");
        MenuItem codeButton3 = new MenuItem("Optimize GUI Triggers");

        codeMenu.getItems().add(codeButton1);
        codeMenu.getItems().add(codeButton2);
        codeMenu.getItems().add(codeButton3);

        codeButton1.setOnAction(controller::reformatCode);
        codeButton2.setOnAction(controller::minifyCode);
        codeButton3.setOnAction(controller::optimizeGui);

        menuBar.getMenus().add(codeMenu);
    }

    private void makeEditMenu(Controller controller, MenuBar menuBar) {
        Menu editMenu = new Menu("Edit");

        MenuItem editButton1 = new MenuItem("Add NZCP");
        MenuItem editButton2 = new MenuItem("Add JJCP");
        MenuItem editButton3 = new MenuItem("Add NZCP (Deduplicate)");
        MenuItem editButton4 = new MenuItem("Add JJCP (Deduplicate)");
        MenuItem editButton5 = new MenuItem("Merge Scripts");
        MenuItem editButton6 = new MenuItem("Merge Scripts (Deduplicate)");
        MenuItem editButton7 = new MenuItem("Scramble Names");
        MenuItem editButton8 = new MenuItem("Rename Function");
        MenuItem editButton9 = new MenuItem("Rename Variable");


        editButton1.setOnAction(controller::addNzcp);
        editButton2.setOnAction(controller::addJjcp);
        editButton3.setOnAction(controller::addNzcpD);
        editButton4.setOnAction(controller::addJjcpD);
        editButton5.setOnAction(controller::mergeScript);
        editButton6.setOnAction(controller::mergeScriptD);
        editButton7.setOnAction(controller::scrambleNames);
        editButton8.setOnAction(controller::renameScriptFunction);
        editButton9.setOnAction(controller::renameScriptVariable);

        editMenu.getItems().add(editButton1);
        editMenu.getItems().add(editButton2);
        editMenu.getItems().add(editButton3);
        editMenu.getItems().add(editButton4);
        editMenu.getItems().add(editButton5);
        editMenu.getItems().add(editButton6);
        editMenu.getItems().add(editButton7);
        editMenu.getItems().add(editButton8);
        editMenu.getItems().add(editButton9);

        menuBar.getMenus().add(editMenu);
    }



    public void main(String[] args) {
        launch(args);
    }
}
