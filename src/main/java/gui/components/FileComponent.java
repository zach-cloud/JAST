package gui.components;

import interfaces.IFileWriterService;
import javafx.scene.control.TreeItem;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import services.FileWriterService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public final class FileComponent extends GenericComponent {

    private FileChooser openFileChooser;
    private FileChooser writeFileChooser;
    private StatusComponent statusComponent;
    private ConfigLoaderComponent configLoaderComponent;
    private RawcodeComponent rawcodeComponent;
    private MpqComponent mpqComponent;
    private IFileWriterService writerService;
    private SolutionExplorerComponent solutionExplorerComponent;

    private String openPath = "";
    private File mpqPath;

    public FileComponent(ComponentContext context, StatusComponent statusComponent,
                         ConfigLoaderComponent configLoaderComponent, RawcodeComponent rawcodeComponent,
                         MpqComponent mpqComponent, SolutionExplorerComponent solutionExplorerComponent) {
        super(context);
        this.openFileChooser = new FileChooser();
        this.writeFileChooser = new FileChooser();
        this.statusComponent = statusComponent;
        this.configLoaderComponent = configLoaderComponent;
        this.rawcodeComponent = rawcodeComponent;
        this.mpqComponent = mpqComponent;
        this.writerService = new FileWriterService();
        this.solutionExplorerComponent = solutionExplorerComponent;
        addFilters(openFileChooser);
        addFilters(writeFileChooser);
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
     * Reads the provided file as text
     *
     * @param file File to read
     */
    private void genericReadText(File file) {
        try {
            context.jassCodeEditor.replaceText(FileUtils.readFileToString(file, Charset.defaultCharset()));
            this.openPath = file.getAbsolutePath();
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
        context.openType = ComponentContext.OpenType.SCRIPT;
        genericReadText(file);
    }

    /**
     * Opens the objects file
     *
     * @param file Objects file
     */
    private void openObjects(File file) {
        context.openType = ComponentContext.OpenType.OBJECTSFILE;
        context.objectsFilePath = file.getAbsolutePath();
        rawcodeComponent.generateRawcodes();
    }

    /**
     * Opens the Strings file
     *
     * @param file Strings file
     */
    private void openStrings(File file) {
        context.openType = ComponentContext.OpenType.STRINGS;
        context.stringsFilePath = file.getAbsolutePath();
        genericReadText(file);
    }

    /**
     * Opens the MPQ file and extracts it
     *
     * @param file MPQ file
     */
    private void openMpq(File file) {
        context.openType = ComponentContext.OpenType.MPQ;
        mpqComponent.extractMpq(file.getAbsolutePath(), file.getName());
        this.mpqPath = file;
        solutionExplorerComponent.addSolution(file.getName());
    }

    /**
     * Opens the given text file
     *
     * @param file Text file
     */
    private void openText(File file) {
        context.openType = ComponentContext.OpenType.TEXTFILE;
        genericReadText(file);
    }

    private void openGenericFile(File selectedFile) {
        if (selectedFile != null && !selectedFile.isDirectory() && selectedFile.exists()) {
            String extension = "";
            if (selectedFile.getName().contains(".")) {
                extension = selectedFile.getName().
                        substring(selectedFile.getName().lastIndexOf("."));
            }
            statusComponent.changeStatus("Opening: " + selectedFile.getName());

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
        } else {
            statusComponent.changeStatus("Cannot open file");
        }
    }

    /**
     * Prompts the user to open a file
     */
    public void open() {
        try {
            statusComponent.changeStatus("Prompting file open");
            File selectedFile = openFileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                configLoaderComponent.saveOpenPath(selectedFile);
                openFileChooser.setInitialDirectory(selectedFile.getParentFile());
                long time = System.currentTimeMillis();
                openGenericFile(selectedFile);
                time = System.currentTimeMillis() - time;
                statusComponent.changeStatus("File opened successfully", time);

            } else {
                statusComponent.changeStatus("File loading cancelled");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to open file: " + ex.getMessage());
        }
    }

    private void saveAs(boolean mpq) {
        try {
            statusComponent.changeStatus("Prompting file saveAs");
            File selectedFile = writeFileChooser.showSaveDialog(null);
            if (selectedFile != null) {
                configLoaderComponent.saveWritePath(selectedFile);
                writeFileChooser.setInitialDirectory(selectedFile.getParentFile());
                long time = System.currentTimeMillis();
                if(mpq) {
                    mpqComponent.saveMpq(mpqPath, selectedFile, solutionExplorerComponent.getCurrentProject());
                } else {
                    writerService.writeString(context.jassCodeEditor.getText(), selectedFile.getAbsolutePath());
                }
                time = System.currentTimeMillis() - time;
                statusComponent.changeStatus("File saved successfully", time);
            } else {
                statusComponent.changeStatus("Save cancelled");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to saveAs file.");
        }
    }

    /**
     * Prompts the user to saveAs the file
     */
    public void saveAs() {
        saveAs(false);
    }

    public void saveMpq() {
        saveAs(true);
    }

    public void setInitialOpenDirectory(File currentPath) {
        openFileChooser.setInitialDirectory(currentPath);
    }

    public void setInitialWriteDirectory(File currentPath) {
        openFileChooser.setInitialDirectory(currentPath);
    }

    public File getFile() {
        return openFileChooser.showOpenDialog(null);
    }

    public void open(TreeItem<String> value) {
        File filePath = solutionExplorerComponent.getSolutionPath(value);
        openGenericFile(filePath);
    }

    public void save() {
        if(openPath != null && !openPath.isEmpty()) {
            File file = new File(this.openPath);
            if(file.exists() && !file.isDirectory()) {
                file.delete();
            }
            file.mkdirs();
            file.delete();
            writerService.writeString(context.jassCodeEditor.getText(), file.getAbsolutePath());
        }
    }
}
