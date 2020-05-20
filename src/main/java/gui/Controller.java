package gui;

import helper.CheatpackLoader;
import interfaces.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import model.InputModel;
import mpq.MpqEditor;
import nodes.AbstractFunction;
import nodes.j.Variable;
import nodes.wts.WtsStringsFile;
import org.apache.commons.io.FileUtils;
import org.fxmisc.richtext.CodeArea;
import javafx.stage.PopupWindow;
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

/**
 * Controller for the GUI elements.
 */
public class Controller {

    private static final String CFG_PATH = "init.cfg";
    private static final String CURRENT_PATH_READ = "currentPathRead";
    private static final String CURRENT_PATH_WRITE = "currentPathWrite";

    private List<String> natives;
    private List<String> autocompleteEntries;
    private String[] keywords = {"if", "then", "else", "endif", "function", "takes",
            "nothing", "returns", "endfunction", "globals", "endglobals",
            "loop", "endloop", "exitwhen", "constant", "local",
            "call", "set", "null", "elseif", "return"};
    private String currentAutocompleteWord = "";
    private Pattern pattern;

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

    private Label statusLabel;
    private CodeArea jassCodeEditor;
    private FileChooser openFileChooser;
    private FileChooser writeFileChooser;
    private OpenType openType;
    private Map<String, String> configurations;
    private SearchWindow searchWindow;
    private StringHashWindow stringHashWindow;
    private String objectsFilePath;
    private String stringsFilePath;
    private String mpqPath;
    private boolean formattingDesired = false;

    private List<AbstractFunction> commonFunctions;
    private List<Variable> commonVariables;

    private Subscription highlighting = null;

    private IExitProgramService exitProgramService;
    private IBlizzardLoaderService blizzardLoaderService;
    private IFileWriterService writerService;
    private ICFGService cfgService;
    private IGuiOptimizerService optimizer;
    private IHashBreakService hashBreakService;
    private IHashService hashService;
    private IRawcodeService rawcodeService;
    private ITreeReplaceService treeReplaceService;
    private MpqEditor mpqEditor;

    /**
     * Instantiates all necessary services, etc.
     */
    public Controller() {
        createServices();
        readConfigs();
        natives = new ArrayList<>();
        commonVariables = new ArrayList<>();
        commonFunctions = new ArrayList<>();
        this.autocompleteEntries = new ArrayList<>();
        addFilters(openFileChooser);
        addFilters(writeFileChooser);
    }

    /**
     * Creates the required services for running app.
     */
    private void createServices() {
        this.exitProgramService = new ExitProgramServiceGUI();
        this.blizzardLoaderService = new BlizzardLoaderService();
        this.writerService = new FileWriterService();
        this.cfgService = new CFGService();
        this.openFileChooser = new FileChooser();
        this.writeFileChooser = new FileChooser();
        this.optimizer = new GuiOptimizerService();
        this.hashBreakService = new HashBreakService();
        this.hashService = new HashService();
        this.treeReplaceService = new TreeReplaceService();
        this.configurations = cfgService.readConfigFile(CFG_PATH);
    }

    /**
     * Reads the desired start directory of the file choosers
     */
    private void readConfigs() {
        if (configurations.containsKey(CURRENT_PATH_READ)) {
            File currentPath = new File(configurations.get(CURRENT_PATH_READ));
            if (currentPath.exists()) {
                openFileChooser.setInitialDirectory(currentPath);
            }
        }
        if (configurations.containsKey(CURRENT_PATH_WRITE)) {
            File currentPath = new File(configurations.get(CURRENT_PATH_WRITE));
            if (currentPath.exists()) {
                writeFileChooser.setInitialDirectory(currentPath);
            }
        }
    }

    /**
     * Adds appropriate filters to the file chooser
     *
     * @param fileChooser File chooser to add filters to
     */
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

    /**
     * Sets the status label of this controller
     *
     * @param statusLabel Status label
     */
    public void setStatusLabel(Label statusLabel) {
        this.statusLabel = statusLabel;
    }

    /**
     * Changes the current status
     *
     * @param status Desired status
     */
    private void changeStatus(String status) {
        statusLabel.setText(status);
    }

    /**
     * Changes the current status
     *
     * @param status Desired status
     * @param time   Time taken to finish the last action
     */
    private void changeStatus(String status, long time) {
        statusLabel.setText(status + " (" + time + " ms)");
    }

    /**
     * Sets the available jass code editor
     *
     * @param jassCodeEditor The jass code editor
     */
    public void setJassCodeEditor(CodeArea jassCodeEditor) {
        this.jassCodeEditor = jassCodeEditor;
    }

    /**
     * Reads the provided file as text
     *
     * @param file File to read
     */
    private void genericReadText(File file) {
        try {
            jassCodeEditor.replaceText(FileUtils.readFileToString(file, Charset.defaultCharset()));
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read file: " + ex.getMessage());
        }
    }

    /**
     * Opens the script file
     *
     * @param file Script file
     */
    private void openScript(File file) {
        this.openType = OpenType.SCRIPT;
        genericReadText(file);
    }

    /**
     * Opens the objects file
     *
     * @param file Objects file
     */
    private void openObjects(File file) {
        this.openType = OpenType.OBJECTSFILE;
        this.objectsFilePath = file.getAbsolutePath();
        generateRawcodes(null);
    }

    /**
     * Opens the Strings file
     *
     * @param file Strings file
     */
    private void openStrings(File file) {
        this.openType = OpenType.STRINGS;
        this.stringsFilePath = file.getAbsolutePath();
        genericReadText(file);
    }

    /**
     * Opens the MPQ file and extracts it
     *
     * @param file MPQ file
     */
    private void openMpq(File file) {
        this.openType = OpenType.MPQ;
        this.mpqPath = file.getAbsolutePath();
    }

    /**
     * Opens the given text file
     *
     * @param file Text file
     */
    private void openText(File file) {
        this.openType = OpenType.TEXTFILE;
        genericReadText(file);
    }

    /**
     * Sets up syntax highlighting
     */
    public void setupHighlighting() {
        setupKeywords();
        jassCodeEditor.getVisibleParagraphs().addModificationObserver
                (
                        new VisibleParagraphStyler<>(jassCodeEditor, this::computeHighlighting)
                );

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

    /**
     * Reads keywords from common/blizzard
     *
     * @param tree Keywords tree
     */
    private void addKeywords(ISyntaxTree tree) {
        for (AbstractFunction function : tree.getScript().getFunctionsSection().getFunctions()) {
            natives.add(function.getName());
            commonFunctions.add(function);
        }
        for (Variable variable : tree.getScript().getGlobalsSection().getGlobalVariables()) {
            natives.add(variable.getName());
            commonVariables.add(variable);
        }
    }

    /**
     * Sets up keywords to use in syntax highlighting
     */
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

    /**
     * Computes syntax highlighting for text
     *
     * @param text Text to highlight
     * @return Highlighted spans
     */
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

    /**
     * Prompts the user to open a file
     */
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
            ex.printStackTrace();
            changeStatus("Failed to open file: " + ex.getMessage());
        }
    }

    /**
     * Prompts the user to save the file
     */
    public void save(ActionEvent e) {
        try {
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
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to save file.");
        }
    }

    /**
     * Closes application
     */
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
        formatIfDesired();
        return tree3;
    }

    /**
     * Adds NZCP to the map.
     */
    public void addNzcp(ActionEvent e) {
        applyGeneric(false, "NZCP", "easymode");
    }

    /**
     * Adds JJCP to the map.
     */
    public void addJjcp(ActionEvent e) {
        applyGeneric(false, "JJCP", "wc3edit");
    }

    /**
     * Adds NZCP to the map and deduplicates
     */
    public void addNzcpD(ActionEvent e) {
        applyGeneric(true, "NZCP", "easymode");
    }

    /**
     * Adds JJCP to the map and deduplicates
     */
    public void addJjcpD(ActionEvent e) {
        applyGeneric(true, "JJCP", "wc3edit");
    }

    /**
     * Applies a generic cheatpack to the map
     *
     * @param dedupe           True if CP should be deduped
     * @param cpName           Name of the pack
     * @param defaultActivator Default activator of pack
     */
    private void applyGeneric(boolean dedupe, String cpName, String defaultActivator) {
        try {
            String activator = JOptionPane.showInputDialog("Enter custom activator (no dash)");
            ISyntaxTree userTree = SyntaxTree.readTree(jassCodeEditor.getText());
            ISyntaxTree cpTree = SyntaxTree.readTree(CheatpackLoader.loadCheatpackByName(cpName));
            ISyntaxTree cp = cpTree.renameVariable("\"" + defaultActivator + "\"", "\"" + activator + "\"");
            jassCodeEditor.replaceText(merge(dedupe, userTree, cp).getString());
            formatIfDesired();
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to parse tree.");
        }
    }

    /**
     * Merges code files together
     *
     * @param dedupe True if code should be deduped, false if not
     */
    public void genericFileMerge(boolean dedupe) {
        try {
            long time = System.currentTimeMillis();
            changeStatus("Prompting file open");
            File selectedFile = openFileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                ISyntaxTree tree1 = SyntaxTree.readTree(jassCodeEditor.getText());
                ISyntaxTree tree2 = SyntaxTree.readTree(selectedFile);
                jassCodeEditor.replaceText(merge(dedupe, tree1, tree2).getString());
                formatIfDesired();
            }
            time = System.currentTimeMillis() - time;
            changeStatus("Done with merging", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to parse file");
        }
    }

    /**
     * Merges scripts together without deduplication
     */
    public void mergeScript(ActionEvent e) {
        genericFileMerge(false);
    }

    /**
     * Merges scripts together with deduplication
     */
    public void mergeScriptD(ActionEvent e) {
        genericFileMerge(true);
    }

    /**
     * Renames a script entity generically
     *
     * @param type Rename type to apply
     */
    public void rename(TreeReplaceService.ReplacementType type) {
        long time = System.currentTimeMillis();
        String nameOne = JOptionPane.showInputDialog("Enter name to replace");
        String nameTwo = JOptionPane.showInputDialog("Enter name to replace with");
        ISyntaxTree syntaxTree = SyntaxTree.readTree(jassCodeEditor.getText());
        jassCodeEditor.replaceText(treeReplaceService.replace(type, nameOne, nameTwo, syntaxTree).toString());
        formatIfDesired();
        time = System.currentTimeMillis() - time;
        changeStatus("Renamed successfully", time);
    }

    /**
     * Renames a script variable
     */
    public void renameScriptVariable(ActionEvent e) {
        rename(TreeReplaceService.ReplacementType.VARIABLE);
    }

    /**
     * Renames a script function
     */
    public void renameScriptFunction(ActionEvent e) {
        rename(TreeReplaceService.ReplacementType.FUNCTION);
    }

    /**
     * Optimizes GUI conditions into a single condition and inlines it
     */
    public void optimizeGui(ActionEvent e) {
        try {
            long time = System.currentTimeMillis();
            changeStatus("Reading Syntax Tree");
            ISyntaxTree tree = SyntaxTree.readTree(jassCodeEditor.getText());
            changeStatus("Writing tree");
            String newData = optimizer.optimize(tree).getString();
            jassCodeEditor.replaceText(newData);
            formatIfDesired();
            time = System.currentTimeMillis() - time;
            changeStatus("Optimized GUI", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to parse tree.");
        }
    }

    /**
     * Scrambles all variable and function names
     */
    public void scrambleNames(ActionEvent e) {
        try {
            long time = System.currentTimeMillis();
            changeStatus("Reading Syntax Tree");
            ISyntaxTree tree = SyntaxTree.readTree(jassCodeEditor.getText());
            changeStatus("Writing tree");
            String newData = tree.deduplicate(new RandomNameGeneratorService()).getString();
            jassCodeEditor.replaceText(newData);
            formatIfDesired();
            time = System.currentTimeMillis() - time;
            changeStatus("Scrambled names", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to parse tree.");
        }
    }

    /**
     * If formatting is set to true, will format the code. else does nothing.
     */
    private void formatIfDesired() {
        if (formattingDesired) {
            reformatCode(null);
        }
    }

    /**
     * Reformats the code into a readable format
     */
    public void reformatCode(ActionEvent e) {
        formattingDesired = true;
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
            ex.printStackTrace();
            changeStatus(ex.getMessage());
        }
    }

    /**
     * Minifies/de-formats the code
     */
    public void minifyCode(ActionEvent e) {
        formattingDesired = false;
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
            ex.printStackTrace();
            changeStatus(ex.getMessage());
        }
    }

    /**
     * Generates rawcodes from an open objects file
     * Or prompts open of an objects file if not selected already
     */
    public void generateRawcodes(ActionEvent e) {
        try {
            long time = System.currentTimeMillis();
            rawcodeService = new RawcodeService();
            if (objectsFilePath == null) {
                open(e);
            }
            if (objectsFilePath != null) {
                if (stringsFilePath != null) {
                    rawcodeService.addWTS(stringsFilePath);
                }
                jassCodeEditor.replaceText(rawcodeService.makeRawcodesFrom(objectsFilePath));
            } else {
                JOptionPane.showMessageDialog(null, "Cannot generate rawcodes from specified file.");
            }
            time = System.currentTimeMillis() - time;
            changeStatus("Generated rawcodes", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to generate rawcodes.");
        }
    }

    /**
     * Extracts
     *
     * @param e
     */
    public void extractMpq(ActionEvent e) {
        try {
            long time = System.currentTimeMillis();
            if (mpqPath == null) {
                open(e);
            }
            if (mpqPath != null) {
                File file = new File("tempFiles/");
                if (file.exists()) {
                    file.delete();
                }
                file.mkdirs();
                file.delete();
                mpqEditor = new MpqEditor(new File(mpqPath));
                mpqEditor.extractFiles(file);
            } else {
                JOptionPane.showMessageDialog(null, "Cannot extract from specified file.");
            }
            time = System.currentTimeMillis() - time;
            changeStatus("Extracted to /tempFiles", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            changeStatus("Failed to extract files.");
        }
    }

    public void computeStringhash(ActionEvent e) {
        if (stringHashWindow == null) {
            stringHashWindow = new StringHashWindow(this);
        }
        stringHashWindow.show();
    }

    public void breakStringhash(ActionEvent e) {
        computeStringhash(e);
    }

    public void calculateStringHash(ActionEvent e) {
        InputModel model = new InputModel();
        model.setPlaintext(stringHashWindow.getHashText());
        stringHashWindow.setResult(hashService.runHash(model));
    }

    public void breakStringhashExecute(ActionEvent e) {
        InputModel model = new InputModel();
        model.setHash(stringHashWindow.getHashText());
        hashBreakService.setHash(model);
        stringHashWindow.setResult(hashBreakService.runBreak());
    }

    public void closeStringHash(ActionEvent e) {
        if (stringHashWindow != null) {
            stringHashWindow.hide();
        }
    }

    public void about(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "JAST - Managed JASS Code Modifier\nOpen source: https://github.com/zach-cloud/JAST");
    }

    public void undo(ActionEvent e) {
        jassCodeEditor.undo();
    }

    public void redo(ActionEvent e) {
        jassCodeEditor.redo();
    }

    public void search(ActionEvent e) {
        if (searchWindow == null) {
            searchWindow = new SearchWindow(this);
        }
        searchWindow.show();
    }

    public void searchExecute(ActionEvent e) {
        if (searchWindow != null) {
            regexFind(jassCodeEditor, searchWindow.getSearchText(), jassCodeEditor.getCaretPosition());
        }
    }

    private void regexFind(CodeArea area, String regex, int offset) {
        Matcher matcher = Pattern.compile(Pattern.quote(regex)).matcher(area.getText().substring(offset));
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            area.selectRange(start + offset, end + offset);
            int current = jassCodeEditor.getCurrentParagraph();
            jassCodeEditor.showParagraphAtTop(current);
        } else {
            JOptionPane.showMessageDialog(null, "No more matches found");
        }
    }

    public void closeSearch(ActionEvent e) {
        if (searchWindow != null) {
            searchWindow.hide();
        }
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
                } else if (saveHotkey.match(ke)) {
                    save(null);
                    ke.consume();
                } else if (undoHotkey.match(ke)) {
                    undo(null);
                    ke.consume();
                } else if (redoHotkey.match(ke)) {
                    redo(null);
                    ke.consume();
                } else if (searchHotkey.match(ke)) {
                    search(null);
                    ke.consume();
                }
            }
        });
    }

    public void setupAutocomplete(Scene scene) {
        for (AbstractFunction function : commonFunctions) {
            autocompleteEntries.add(function.getName());
        }
        PopupWindow popup = new Popup();

        jassCodeEditor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (t1.length() > s.length()) {
                    char lastChar = t1.charAt(t1.length() - 1);
                    if (lastChar == ' ' || lastChar == '\n') {
                        currentAutocompleteWord = "";
                    } else {
                        currentAutocompleteWord += lastChar;
                    }
                } else {
                    currentAutocompleteWord = currentAutocompleteWord.substring(0, currentAutocompleteWord.length() - 1);
                }
            }
        });

        Subscription cleanupWhenNoLongerNeedIt = jassCodeEditor
                .multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .subscribe(ignore -> showAutocomplete());
    }

    private void showAutocomplete() {

    }
}
