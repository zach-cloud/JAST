package gui.components;

import interfaces.ICFGService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import services.CFGService;
import java.io.File;
import java.util.Map;

public final class ConfigLoaderComponent extends GenericComponent {

    private static final String CFG_PATH = "init.cfg";
    private static final String CURRENT_PATH_READ = "currentPathRead";
    private static final String CURRENT_PATH_WRITE = "currentPathWrite";
    private static final String CURRENT_THEME = "currentTheme";
    private static final String SCREEN_X = "screenX";
    private static final String SCREEN_Y = "screenY";

    private ICFGService cfgService;
    private Map<String, String> configurations;

    public ConfigLoaderComponent(ComponentContext context) {
        super(context);
        this.cfgService = new CFGService();
        this.configurations = cfgService.readConfigFile(CFG_PATH);
    }

    /**
     * Reads the desired start directory of the file choosers
     */
    public void readConfigs(FileComponent fileComponent) {
        if (configurations.containsKey(CURRENT_PATH_READ)) {
            File currentPath = new File(configurations.get(CURRENT_PATH_READ));
            if (currentPath.exists()) {
                fileComponent.setInitialOpenDirectory(currentPath);
            }
        }
        if (configurations.containsKey(CURRENT_PATH_WRITE)) {
            File currentPath = new File(configurations.get(CURRENT_PATH_WRITE));
            if (currentPath.exists()) {
                fileComponent.setInitialWriteDirectory(currentPath);
            }
        }
        if (configurations.containsKey(CURRENT_THEME)) {
            context.currentTheme = configurations.get(CURRENT_THEME);
        }
        if(configurations.containsKey(SCREEN_X)) {
            try {
                context.screenX = Double.parseDouble(configurations.get(SCREEN_X));
            } catch (NumberFormatException ex) {
                // This was probably corrupted
                context.screenX = -1;
            }
        }
        if(configurations.containsKey(SCREEN_Y)) {
            try {
                context.screenY = Double.parseDouble(configurations.get(SCREEN_Y));
            } catch (NumberFormatException ex) {
                // This was probably corrupted
                context.screenY = -1;
            }
        }
    }

    public void makeElementsFillScreen(Scene scene, Stage stage, Pane root) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        if(context.screenX <= 0 || context.screenY <= 0) {
            context.screenX = primaryScreenBounds.getWidth();
            context.screenY = primaryScreenBounds.getHeight();
            configurations.put(SCREEN_X, (int)context.screenX + "");
            configurations.put(SCREEN_Y, (int)context.screenY + "");
            cfgService.writeConfigFile(CFG_PATH, configurations);
        }
        stage.setX(0);
        stage.setY(0);
        // I have no idea why these adjustments are needed, but they are
        // If left out, the size shrinks
        stage.setWidth(context.screenX + 15);
        stage.setHeight(context.screenY + 38);
        root.setMinWidth(context.screenX + 15);
        root.setMinHeight(context.screenY + 38);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                context.screenX = newSceneWidth.doubleValue();
                configurations.put(SCREEN_X, (int)context.screenX + "");
                cfgService.writeConfigFile(CFG_PATH, configurations);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                context.screenY = newSceneHeight.doubleValue();
                configurations.put(SCREEN_Y, (int)context.screenY + "");
                cfgService.writeConfigFile(CFG_PATH, configurations);
            }
        });
    }

    public void saveTheme() {
        configurations.put(CURRENT_THEME, context.currentTheme);
        cfgService.writeConfigFile(CFG_PATH, configurations);
    }

    public void saveOpenPath(File selectedFile) {
        configurations.put(CURRENT_PATH_READ, selectedFile.getParent());
        cfgService.writeConfigFile(CFG_PATH, configurations);
    }

    public void saveWritePath(File selectedFile) {
        configurations.put(CURRENT_PATH_WRITE, selectedFile.getParent());
        cfgService.writeConfigFile(CFG_PATH, configurations);
    }
}
