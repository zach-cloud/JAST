package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {
        MenuBar menuBar = makeMenus();
        VBox root = new VBox(menuBar);
        TextArea jassCodeEditor = setupEditorBox(root);
        setupScene(stage, root);
        makeElementsFillScreen(stage, root);
        bindElementSizes(stage, root, jassCodeEditor);
        stage.show();
        setBackgroundColor(jassCodeEditor);
    }

    private MenuBar makeMenus() {
        Controller controller = new Controller();
        MenuBar menuBar = new MenuBar();
        makeFileMenu(controller, menuBar);
        makeEditMenu(controller, menuBar);
        return menuBar;
    }

    private TextArea setupEditorBox(VBox root) {
        final TextArea jassCodeEditor = new TextArea();
        jassCodeEditor.setWrapText(true);
        root.getChildren().add(jassCodeEditor);
        return jassCodeEditor;
    }

    private void setupScene(Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("JASS AST Modifier");
    }

    private void bindElementSizes(Stage stage, VBox root, TextArea jassCodeEditor) {
        root.prefHeightProperty().bind(stage.heightProperty());
        jassCodeEditor.prefHeightProperty().bind(root.heightProperty());
    }

    private void setBackgroundColor(TextArea jassCodeEditor) {
        Region region = ( Region ) jassCodeEditor.lookup( ".content" );
        region.setBackground( new Background( new BackgroundFill( Color.rgb(43,43, 43), CornerRadii.EMPTY, Insets.EMPTY ) ) );
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

    private void makeEditMenu(Controller controller, MenuBar menuBar) {
        Menu editMenu = new Menu("Edit");

        MenuItem editButton1 = new MenuItem("Add NZCP");
        MenuItem editButton2 = new MenuItem("Add JJCP");
        MenuItem editButton3 = new MenuItem("Add NZCP (Deduplicate)");
        MenuItem editButton4 = new MenuItem("Add JJCP (Deduplicate)");
        MenuItem editButton5 = new MenuItem("Merge Scripts");
        MenuItem editButton6 = new MenuItem("Merge Scripts (Deduplicate)");
        MenuItem editButton7 = new MenuItem("Rename Function");
        MenuItem editButton8 = new MenuItem("Rename Variable");
        MenuItem editButton9 = new MenuItem("Optimize GUI Triggers");

        editButton1.setOnAction(controller::open);

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
