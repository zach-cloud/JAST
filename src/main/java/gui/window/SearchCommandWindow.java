package gui.window;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class SearchCommandWindow extends CustomCommandWindow {

    private TextField searchText;
    private Button searchButton;
    private Button exitButton;

    public SearchCommandWindow(Controller controller) {
        super(controller);
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

        setupScene(stage, root, "Search");
    }

    public String getSearchText() {
        return searchText.getText();
    }
}
