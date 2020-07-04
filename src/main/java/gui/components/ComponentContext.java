package gui.components;

import interfaces.IBlizzardLoaderService;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nodes.AbstractFunction;
import nodes.j.Variable;
import org.fxmisc.richtext.CodeArea;
import services.BlizzardLoaderService;

import java.util.ArrayList;
import java.util.List;

public class ComponentContext {

    /**
     * Determines which type of file was opened.
     * TODO: Do I need this?
     */
    enum OpenType {
        MPQ,
        SCRIPT,
        TEXTFILE,
        OBJECTSFILE,
        STRINGS
    }

    CodeArea jassCodeEditor;
    CodeArea functionBrowser;
    Label statusLabel;
    VBox root;
    Stage stage;

    OpenType openType;
    String objectsFilePath;
    String stringsFilePath;
    String mpqPath;
    boolean formattingDesired = false;

    /**
     * Keywords components
     */
    List<String> natives;
    List<String> types;
    List<String> autocompleteEntries;
    String[] keywords = {"if", "then", "else", "endif", "function", "takes",
            "nothing", "returns", "endfunction", "globals", "endglobals",
            "loop", "endloop", "exitwhen", "constant", "local",
            "call", "set", "null", "elseif", "return", "library", "endlibrary",
            "scope", "endscope", "struct", "endstruct", "extends", "initializer",
            "method", "endmethod"};

    List<AbstractFunction> commonFunctions;
    List<Variable> commonVariables;

    /**
     * Services components
     */
    IBlizzardLoaderService blizzardLoaderService;

    double screenX = -1;
    double screenY = -1;
    String currentTheme;

    public ComponentContext(CodeArea jassCodeEditor, CodeArea functionBrowser, VBox root, Stage stage, Label statusLabel) {
        this.jassCodeEditor = jassCodeEditor;
        this.functionBrowser = functionBrowser;
        this.root = root;
        this.stage = stage;
        this.statusLabel = statusLabel;
        this.natives = new ArrayList<>();
        this.types = new ArrayList<>();
        this.commonVariables = new ArrayList<>();
        this.commonFunctions = new ArrayList<>();
        this.autocompleteEntries = new ArrayList<>();
        this.blizzardLoaderService = new BlizzardLoaderService();
    }
}
