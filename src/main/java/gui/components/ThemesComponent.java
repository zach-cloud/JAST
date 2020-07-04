package gui.components;

import gui.GUI;
import javafx.scene.Scene;

public final class ThemesComponent extends GenericComponent {

    private ConfigLoaderComponent configLoaderComponent;

    private String jasscraftTheme;
    private String darkTheme;
    private String lightTheme;

    public ThemesComponent(ComponentContext context, ConfigLoaderComponent configLoaderComponent) {
        super(context);
        this.configLoaderComponent = configLoaderComponent;
        setupStyles();
    }

    public void applyDefault(Scene scene) {
        if (context.currentTheme != null && !context.currentTheme.isEmpty()) {
            if (context.currentTheme.equalsIgnoreCase("lightTheme")) {
                applyLightTheme(scene);
            } else if (context.currentTheme.equalsIgnoreCase("jasscraft")) {
                applyJasscraftTheme(scene);
            } else {
                applyDarkTheme(scene);
            }
        } else {
            applyDarkTheme(scene);
        }
    }

    public void applyJasscraftTheme(Scene scene) {
        scene.getStylesheets().add(jasscraftTheme);
        scene.getStylesheets().remove(darkTheme);
        scene.getStylesheets().remove(lightTheme);
        configLoaderComponent.saveTheme();
    }

    public void applyLightTheme(Scene scene) {
        scene.getStylesheets().add(lightTheme);
        scene.getStylesheets().remove(darkTheme);
        scene.getStylesheets().remove(jasscraftTheme);
        configLoaderComponent.saveTheme();
    }

    public void applyDarkTheme(Scene scene) {
        scene.getStylesheets().add(darkTheme);
        scene.getStylesheets().remove(lightTheme);
        scene.getStylesheets().remove(jasscraftTheme);
        context.currentTheme = "darkTheme";
        configLoaderComponent.saveTheme();
    }

    private void setupStyles() {
        jasscraftTheme = GUI.class.getResource("jass-keywords-jasscraft.css").toExternalForm();
        darkTheme = GUI.class.getResource("jass-keywords-darktheme.css").toExternalForm();
        lightTheme = GUI.class.getResource("jass-keywords-lighttheme.css").toExternalForm();
    }

}
