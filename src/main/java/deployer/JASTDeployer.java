package deployer;

import helper.FileHelper;
import org.apache.commons.io.FileUtils;
import settings.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to help automatically create a Release Package of JAST
 */
public class JASTDeployer {

    private final String VERSION = "1.1.4";
    private final String USER_PATH = System.getProperty("user.dir") + "\\";

    private final String JAR_PATH = USER_PATH + "target\\JAST-" + VERSION + ".jar";
    private final String LICENSE_PATH = USER_PATH + "LICENSE";
    private final String LISTFILE_PATH = USER_PATH + "listfile.txt";
    private final String TEMPLATES_PATH = USER_PATH + "templates\\";
    private final String JASSHELPER_PATH = USER_PATH + "jasshelper\\";
    private final String FROZENMPQ_PATH = USER_PATH + "mpq\\";
    private final String RUN_CONTENTS = "java -Xmx1g -jar JAST-" + VERSION + ".jar gui";
    private final String RUN_CLI_CONTENTS = "java -Xmx1g -jar JAST-" + VERSION + ".jar\nread  -n 1 -p \"Press any key to exit\"";

    private final String RELEASES_DESTINATION = USER_PATH + "Releases\\ReleasePackage" + VERSION + "\\";
    private final String JAR_DESTINATION = RELEASES_DESTINATION + "JAST-" + VERSION + ".jar";
    private final String TEMPLATES_DESTINATION = RELEASES_DESTINATION + "templates\\";
    private final String JASSHELPER_DESTINATION = RELEASES_DESTINATION + "jasshelper\\";
    private final String FROZENMPQ_DESTINATION = RELEASES_DESTINATION + "mpq\\";
    private final String LISTFILE_DESTINATION = RELEASES_DESTINATION + "listfile.txt";
    private final String LICENSE_DESTINATION = RELEASES_DESTINATION + "LICENSE";
    private final String RUN_DESTINATION = RELEASES_DESTINATION + "run.sh";
    private final String RUN_CLI_DESTINATION = RELEASES_DESTINATION + "run-cli.sh";

    enum Cheatpacks {
        NZCP,
        JJCP,
        FAI
    }

    /**
     * Default constructor. Nothing to initialize.
     */
    public JASTDeployer() {

    }

    /**
     * Copies all JAST-related files into a ReleasePackage directory
     * Assumed that "mvn package" was already executed
     */
    public void run() throws IOException {
        copyFile(JAR_PATH, JAR_DESTINATION);
        copyFile(LICENSE_PATH, LICENSE_DESTINATION);
        copyFile(LISTFILE_PATH, LISTFILE_DESTINATION);
        writeFileContents(RUN_CONTENTS, RUN_DESTINATION);
        writeFileContents(RUN_CLI_CONTENTS, RUN_CLI_DESTINATION);
        copyFolder(TEMPLATES_PATH, TEMPLATES_DESTINATION);
        copyFolder(JASSHELPER_PATH, JASSHELPER_DESTINATION);
        copyFolder(FROZENMPQ_PATH, FROZENMPQ_DESTINATION);
        for(Cheatpacks cheatpack : Cheatpacks.values()) {
            removeCheatpack(cheatpack.name());
        }
    }

    /**
     * Removes content/metadata for a cheatpack.
     *
     * @param name  Name to remove
     */
    private void removeCheatpack(String name) {
        deleteFile(TEMPLATES_DESTINATION + "content\\" + name);
        deleteFile(TEMPLATES_DESTINATION + "metadata\\" + name);
    }

    /**
     * Deletes a file from disk
     *
     * @param filePath  File to delete
     */
    private void deleteFile(String filePath) {
        System.out.println("Deleting: " + filePath);
        new File(filePath).delete();
    }

    /**
     * Copies all files within a folder.
     *
     * @param path          Source folder
     * @param destination   Destination folder
     */
    private void copyFolder(String path, String destination) throws IOException {
        File origin = new File(path);
        List<File> recursiveFiles = new ArrayList<>();
        FileHelper.recursiveFolderDiscovery(origin, new ArrayList<>(), recursiveFiles);
        for(File file : recursiveFiles) {
            String newPath = file.getAbsolutePath().replace(path, destination);
            copyFile(file.getAbsolutePath(), newPath);
        }
    }

    /**
     * Writes the string to a file
     *
     * @param contents      Data to write
     * @param destination   Destination file
     */
    private void writeFileContents(String contents, String destination) throws IOException {
        FileUtils.write(new File(destination), contents, Charset.defaultCharset());
        System.out.println("Wrote file: " + destination);
    }

    /**
     * Copies a file
     *
     * @param path          Source file
     * @param destination   Destination file
     */
    private void copyFile(String path, String destination) throws IOException {
        System.out.println("Copying file: " + path + " to: " + destination);
        File file = new File(path);
        if(!file.exists()) {
            throw new IOException("File does not exist: " + path);
        }
        File dest = new File(destination);
        if(dest.exists()) {
            dest.delete();
        }
        dest.mkdirs();
        dest.delete();

        byte[] fileBytes = FileUtils.readFileToByteArray(file);
        FileUtils.writeByteArrayToFile(dest, fileBytes);

    }

    /**
     * Main method to execute and deploy a JAST package.
     *
     * @param args  Ignored
     */
    public static void main(String[] args) throws IOException {
        new JASTDeployer().run();
    }
}
