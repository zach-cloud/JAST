package gui;

import helper.CheatpackLoader;
import interfaces.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import nodes.AbstractFunction;
import nodes.j.Variable;
import nodes.wts.WtsStringsFile;
import org.apache.commons.io.FileUtils;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;
import services.*;
import tree.SyntaxTree;
import tree.TreeContext;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private static final String CFG_PATH = "init.cfg";
    public static final String CURRENT_PATH_READ = "currentPathRead";
    public static final String CURRENT_PATH_WRITE = "currentPathWrite";
    private List<String> natives;
    private String[] keywords = {"if", "then", "else", "endif", "function", "takes",
            "nothing", "returns", "endfunction", "globals", "endglobals",
            "loop", "endloop", "exitwhen", "constant", "local",
            "call", "set", "null", "elseif", "return"};

    private Pattern pattern;

    enum OpenType {
        MPQ,
        SCRIPT,
        TEXTFILE,
        OBJECTSFILE,
        STRINGS
    }

    private Label statusLabel;
    private CodeArea jassCodeEditor;
    private FileChooser openFileChooser;
    private FileChooser writeFileChooser;
    private OpenType openType;
    private Map<String, String> configurations;

    private IExitProgramService exitProgramService;
    private IBlizzardLoaderService blizzardLoaderService;
    private IFileWriterService writerService;
    private ICFGService cfgService;
    private IGuiOptimizerService optimizer;

    public Controller() {
        this.exitProgramService = new ExitProgramServiceGUI();
        this.blizzardLoaderService = new BlizzardLoaderService();
        this.writerService = new FileWriterService();
        this.cfgService = new CFGService();
        this.openFileChooser = new FileChooser();
        this.writeFileChooser = new FileChooser();
        this.optimizer = new GuiOptimizerService();
        this.configurations = cfgService.readConfigFile(CFG_PATH);
        if (configurations.containsKey(CURRENT_PATH_READ)) {
            openFileChooser.setInitialDirectory(new File(configurations.get(CURRENT_PATH_READ)));
        }
        if (configurations.containsKey(CURRENT_PATH_WRITE)) {
            writeFileChooser.setInitialDirectory(new File(configurations.get(CURRENT_PATH_WRITE)));
        }
        natives = new ArrayList<>();
        addFilters(openFileChooser);
        addFilters(writeFileChooser);
    }

    private void addFilters(FileChooser fileChooser) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Any files", "*.*"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Script files (*.j)", "*.j"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("WC3 Maps (*.w3x)", "*.w3x"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("String Files (*.wts)", "*.wts"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Unit Files (*.w3u)", "*.w3u"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ability Files (*.w3u)", "*.w3a"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Item Files (*.w3u)", "*.w3t"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
    }

    public Label getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(Label statusLabel) {
        this.statusLabel = statusLabel;
    }

    private void changeStatus(String status) {
        statusLabel.setText(status);
    }

    private void changeStatus(String status, long time) {
        statusLabel.setText(status + " (" + time + " ms)");
    }

    public CodeArea getJassCodeEditor() {
        return jassCodeEditor;
    }

    public void setJassCodeEditor(CodeArea jassCodeEditor) {
        this.jassCodeEditor = jassCodeEditor;
    }

    private void genericReadText(File file) {
        try {
            jassCodeEditor.replaceText(FileUtils.readFileToString(file, Charset.defaultCharset()));
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read file: " + ex.getMessage());
        }
    }

    private void openScript(File file) {
        this.openType = OpenType.SCRIPT;
        genericReadText(file);
    }

    private void openObjects(File file) {
        this.openType = OpenType.OBJECTSFILE;

    }

    private void openStrings(File file) {
        this.openType = OpenType.STRINGS;
        genericReadText(file);
    }

    private void openMpq(File file) {
        this.openType = OpenType.MPQ;

    }

    private void openText(File file) {
        this.openType = OpenType.TEXTFILE;
        genericReadText(file);
    }

    public void setupHighlighting() {
        setupKeywords();
        Subscription cleanupWhenNoLongerNeedIt = jassCodeEditor
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(100))
                .subscribe(ignore -> jassCodeEditor.setStyleSpans(0, computeHighlighting(jassCodeEditor.getText())));

        final Pattern whiteSpace = Pattern.compile("^\\s+");
        jassCodeEditor.addEventHandler(KeyEvent.KEY_PRESSED, KE ->
        {
            if (KE.getCode() == KeyCode.ENTER) {
                int caretPosition = jassCodeEditor.getCaretPosition();
                int currentParagraph = jassCodeEditor.getCurrentParagraph();
                Matcher m0 = whiteSpace.matcher(jassCodeEditor.getParagraph(currentParagraph - 1).getSegments().get(0));
                if (m0.find()) Platform.runLater(() -> jassCodeEditor.insertText(caretPosition, m0.group()));
            }
        });
    }

    private void addKeywords(ISyntaxTree tree) {
        for (AbstractFunction function : tree.getScript().getFunctionsSection().getFunctions()) {
            natives.add(function.getName());
        }
        for (Variable variable : tree.getScript().getGlobalsSection().getGlobalVariables()) {
            natives.add(variable.getName());
        }
    }

    private void setupKeywords() {
        addKeywords(blizzardLoaderService.loadCommon());
        addKeywords(blizzardLoaderService.loadBlizzard());
        natives.add("string");
        natives.add("integer");
        natives.add("real");
        natives.add("boolean");
        String[] array = natives.toArray(new String[0]);
        String KEYWORDS_PATTERN = "\\b(" + String.join("|", keywords) + ")\\b";
        String NATIVE_PATTERN = "\\b(" + String.join("|", array) + ")\\b";
        String PAREN_PATTERN = "\\(|\\)";
        String BRACKET_PATTERN = "\\[|\\]";
        String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
        String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

        pattern = Pattern.compile(
                "(?<NATIVE>" + NATIVE_PATTERN + ")"
                        + "|(?<KEYWORD>" + KEYWORDS_PATTERN + ")"
                        + "|(?<PAREN>" + PAREN_PATTERN + ")"
                        + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                        + "|(?<STRING>" + STRING_PATTERN + ")"
                        + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
        );
    }

    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = pattern.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass =
                    matcher.group("NATIVE") != null ? "native" :
                            matcher.group("KEYWORD") != null ? "keyword" :
                                    matcher.group("PAREN") != null ? "paren" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("STRING") != null ? "string" :
                                                            matcher.group("COMMENT") != null ? "comment" :
                                                                    null; /* never happens */
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

    public void open(ActionEvent e) {
        try {
            changeStatus("Prompting file open");
            File selectedFile = openFileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                configurations.put(CURRENT_PATH_READ, selectedFile.getParent());
                cfgService.writeConfigFile(CFG_PATH, configurations);
                if (selectedFile.exists()) {
                    String extension = "";
                    if (selectedFile.getName().contains(".")) {
                        extension = selectedFile.getName().
                                substring(selectedFile.getName().lastIndexOf("."));
                    }
                    changeStatus("Opening: " + selectedFile.getName());
                    long time = System.currentTimeMillis();
                    if (extension.equals(".j")) {
                        openScript(selectedFile);
                    } else if (extension.equals(".w3t") || extension.equals(".w3u") ||
                            extension.equals(".w3a") || extension.equals(".w3b")) {
                        openObjects(selectedFile);
                    } else if (extension.equals(".wts")) {
                        openStrings(selectedFile);
                    } else if (extension.equals(".w3x") || extension.equals(".w3m") ||
                            extension.equals(".mpq")) {
                        openMpq(selectedFile);
                    } else {
                        openText(selectedFile);
                    }
                    time = System.currentTimeMillis() - time;
                    changeStatus("File opened successfully", time);
                } else {
                    changeStatus("File not loaded: does not exist");
                }
            } else {
                changeStatus("File loading cancelled");
            }
        } catch (Exception ex) {
            changeStatus("Failed to open file: " + ex.getMessage());
        }
    }

    public void save(ActionEvent e) {
        changeStatus("Prompting file open");
        File selectedFile = writeFileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            configurations.put(CURRENT_PATH_WRITE, selectedFile.getParent());
            cfgService.writeConfigFile(CFG_PATH, configurations);
            long time = System.currentTimeMillis();
            writerService.writeString(jassCodeEditor.getText(), selectedFile.getAbsolutePath());
            time = System.currentTimeMillis() - time;
            changeStatus("File saved successfully", time);
        } else {
            changeStatus("Save cancelled");
        }
    }

    public void close(ActionEvent e) {
        changeStatus("Exiting program");
        exitProgramService.exit();
    }

    /**
     * Merges code files together based on input.
     *
     * @param dedupe True if code should be de-duped before inserting
     * @param tree1  First syntax tree to merge
     * @param tree2  Second syntax tree to merge
     */
    public ISyntaxTree merge(boolean dedupe, ISyntaxTree tree1, ISyntaxTree tree2) {
        if (dedupe) {
            changeStatus("De-duplicating variables/functions");
            tree2 = tree2.deduplicate(new RandomNameGeneratorService());
            changeStatus("Completed variable/function deduplication");
        }
        ISyntaxTree tree3 = tree1.merge(tree2);
        changeStatus("Merged into " + tree3.getScript().getGlobalsSection().getGlobalVariables().size() + " variables and " + tree3.getScript().getFunctionsSection().getFunctions().size() + " functions.");
        jassCodeEditor.replaceText(tree3.getString());
        return tree3;
    }

    public void addNzcp(ActionEvent e) {
        applyGeneric(false, "NZCP", "easymode");
    }

    public void addJjcp(ActionEvent e) {
        applyGeneric(false, "JJCP", "wc3edit");
    }

    public void addNzcpD(ActionEvent e) {
        applyGeneric(true, "NZCP", "easymode");
    }

    public void addJjcpD(ActionEvent e) {
        applyGeneric(true, "JJCP", "wc3edit");
    }

    private void applyGeneric(boolean dedupe, String cpName, String defaultActivator) {
        String activator = JOptionPane.showInputDialog("Enter custom activator (no dash)");
        ISyntaxTree userTree = SyntaxTree.readTree(jassCodeEditor.getText());
        ISyntaxTree cpTree = SyntaxTree.readTree(CheatpackLoader.loadCheatpackByName(cpName));
        ISyntaxTree cp = cpTree.renameVariable("\"" + defaultActivator + "\"", "\"" + activator + "\"");
        jassCodeEditor.replaceText(merge(dedupe, userTree, cp).getString());
    }

    public void genericFileMerge(boolean dedupe) {
        try {
            long time = System.currentTimeMillis();
            changeStatus("Prompting file open");
            File selectedFile = openFileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                ISyntaxTree tree1 = SyntaxTree.readTree(jassCodeEditor.getText());
                ISyntaxTree tree2 = SyntaxTree.readTree(selectedFile);
                jassCodeEditor.replaceText(merge(dedupe, tree1, tree2).getString());
            }
            time = System.currentTimeMillis() - time;
            changeStatus("Done with merging", time);
        } catch (Exception ex) {
            changeStatus(ex.getMessage());
        }
    }

    public void mergeScript(ActionEvent e) {
        genericFileMerge(false);
    }

    public void mergeScriptD(ActionEvent e) {
        genericFileMerge(true);
    }

    public void renameScriptVariable(ActionEvent e) {

    }

    public void renameScriptFunction(ActionEvent e) {

    }

    public void optimizeGui(ActionEvent e) {
        long time = System.currentTimeMillis();
        changeStatus("Reading Syntax Tree");
        ISyntaxTree tree = SyntaxTree.readTree(jassCodeEditor.getText());
        changeStatus("Writing tree");
        String newData = optimizer.optimize(tree).getString();
        jassCodeEditor.replaceText(newData);
        time = System.currentTimeMillis() - time;
        changeStatus("Optimized GUI", time);
    }

    public void scrambleNames(ActionEvent e) {
        long time = System.currentTimeMillis();
        changeStatus("Reading Syntax Tree");
        ISyntaxTree tree = SyntaxTree.readTree(jassCodeEditor.getText());
        changeStatus("Writing tree");
        String newData = tree.deduplicate(new RandomNameGeneratorService()).getString();
        jassCodeEditor.replaceText(newData);
        time = System.currentTimeMillis() - time;
        changeStatus("Scrambled names", time);
    }

    public void reformatCode(ActionEvent e) {
        try {
            if (openType == OpenType.SCRIPT || openType == OpenType.TEXTFILE) {
                long time = System.currentTimeMillis();
                changeStatus("Reading Syntax Tree");
                ISyntaxTree tree = SyntaxTree.readTree(jassCodeEditor.getText());
                changeStatus("Writing tree");
                jassCodeEditor.replaceText(tree.getFormatted());
                time = System.currentTimeMillis() - time;
                changeStatus("Formatted JASS code", time);
            } else if (openType == OpenType.STRINGS) {
                long time = System.currentTimeMillis();
                changeStatus("Reading WTS Tree");
                WtsStringsFile stringsFile = new WtsStringsFile(new Scanner(jassCodeEditor.getText()), new TreeContext());
                changeStatus("Writing WTS Tree");
                jassCodeEditor.replaceText(stringsFile.toFormattedString(0));
                time = System.currentTimeMillis() - time;
                changeStatus("Formatted WTS code", time);
            }
        } catch (Exception ex) {
            changeStatus(ex.getMessage());
        }
    }

    public void minifyCode(ActionEvent e) {
        try {
            if (openType == OpenType.SCRIPT || openType == OpenType.TEXTFILE) {
                long time = System.currentTimeMillis();
                changeStatus("Reading Syntax Tree");
                ISyntaxTree tree = SyntaxTree.readTree(jassCodeEditor.getText());
                changeStatus("Writing tree");
                jassCodeEditor.replaceText(tree.getString());
                time = System.currentTimeMillis() - time;
                changeStatus("Wrote JASS code", time);
            } else if (openType == OpenType.STRINGS) {
                long time = System.currentTimeMillis();
                changeStatus("Reading WTS Tree");
                WtsStringsFile stringsFile = new WtsStringsFile(new Scanner(jassCodeEditor.getText()), new TreeContext());
                changeStatus("Writing WTS Tree");
                jassCodeEditor.replaceText(stringsFile.toString());
                time = System.currentTimeMillis() - time;
                changeStatus("Wrote WTS code", time);
            }
        } catch (Exception ex) {
            changeStatus(ex.getMessage());
        }
    }

    public void generateRawcodes(ActionEvent e) {

    }

    public void computeStringhash(ActionEvent e) {

    }

    public void breakStringhash(ActionEvent e) {

    }

    public void about(ActionEvent e) {

    }

    public void undo(ActionEvent e) {
        jassCodeEditor.undo();
    }

    public void redo(ActionEvent e) {
        jassCodeEditor.redo();
    }

    public void search(ActionEvent e) {
        Platform.runLater(() -> jassCodeEditor.moveTo(jassCodeEditor.position(3, 0).toOffset()));
        jassCodeEditor.showParagraphAtTop(57);
    }

    public void setupHotkeys(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination openHotkey = new KeyCodeCombination(KeyCode.O,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination saveHotkey = new KeyCodeCombination(KeyCode.S,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination undoHotkey = new KeyCodeCombination(KeyCode.Z,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination redoHotkey = new KeyCodeCombination(KeyCode.Y,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination searchHotkey = new KeyCodeCombination(KeyCode.F,
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (openHotkey.match(ke)) {
                    open(null);
                    ke.consume();
                } else if(saveHotkey.match(ke)) {
                    save(null);
                    ke.consume();
                } else if(undoHotkey.match(ke)) {
                    undo(null);
                    ke.consume();
                } else if(redoHotkey.match(ke)) {
                    redo(null);
                    ke.consume();
                } else if(searchHotkey.match(ke)) {
                    search(null);
                    ke.consume();
                }
            }
        });
    }
}
