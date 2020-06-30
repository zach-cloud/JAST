package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class SearchWindow extends Application {

    private Controller controller;
    private TextField searchText;
    private Button searchButton;
    private Button exitButton;
    private Stage stage;

    public SearchWindow(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        this.searchText = new TextField();
        this.searchButton = new Button("Search");
        this.exitButton = new Button("Exit");
        root.getChildren().add(new Label("Search for: "));
        root.getChildren().add(searchText);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(searchButton);
        root.getChildren().add(new Label(" "));
        root.getChildren().add(exitButton);

        searchButton.setOnAction(controller::searchExecute);
        exitButton.setOnAction(controller::closeSearch);

        setupScene(controller, stage, root);
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();
    }

    private void setupScene(Controller controller, Stage stage, VBox root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getStylesheets().add(GUI.class.getResource("jass-keywords-darktheme.css").toExternalForm());
        stage.setTitle("Search");
    }

    public void show() {
        if(this.stage == null) {
            this.stage = new Stage();
        }
        this.start(stage);
    }

    public void hide() {
        stage.hide();
    }

    String getSearchText() {
        return searchText.getText();
    }
}
