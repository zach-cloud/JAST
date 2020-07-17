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

    private final String VERSION = "1.1.3";
    private final String USER_PATH = System.getProperty("user.dir") + "\\";

    private final String JAR_PATH = USER_PATH + "target\\JAST-" + VERSION + ".jar";
    private final String LICENSE_PATH = USER_PATH + "LICENSE";
    private final String CHEATPACKS_PATH = USER_PATH + "cheatpacks\\";
    private final String JASSHELPER_PATH = USER_PATH + "jasshelper\\";
    private final String FROZENMPQ_PATH = USER_PATH + "mpq\\";
    private final String RUN_CONTENTS = "java -Xmx1g -jar JAST-" + VERSION + ".jar gui";
    private final String RUN_CLI_CONTENTS = "java -Xmx1g -jar JAST-" + VERSION + ".jar\nread  -n 1 -p \"Press any key to exit\"";

    private final String RELEASES_DESTINATION = USER_PATH + "Releases\\ReleasePackage" + VERSION + "\\";
    private final String JAR_DESTINATION = RELEASES_DESTINATION + "JAST-" + VERSION + ".jar";
    private final String BLIZZARD_DESTINATION = RELEASES_DESTINATION + "blizzard\\";
    private final String CHEATPACKS_DESTINATION = RELEASES_DESTINATION + "cheatpacks\\";
    private final String JASSHELPER_DESTINATION = RELEASES_DESTINATION + "jasshelper\\";
    private final String FROZENMPQ_DESTINATION = RELEASES_DESTINATION + "jasshelper\\";
    private final String LICENSE_DESTINATION = RELEASES_DESTINATION + "LICENSE";
    private final String RUN_DESTINATION = RELEASES_DESTINATION + "run.sh";
    private final String RUN_CLI_DESTINATION = RELEASES_DESTINATION + "run-cli.sh";

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
        writeFileContents(RUN_CONTENTS, RUN_DESTINATION);
        writeFileContents(RUN_CLI_CONTENTS, RUN_CLI_DESTINATION);
        if(Settings.CHEATING_ENABLED) {
            copyFolder(CHEATPACKS_PATH, CHEATPACKS_DESTINATION);
        }
        copyFolder(JASSHELPER_PATH, JASSHELPER_DESTINATION);
        copyFolder(FROZENMPQ_PATH, FROZENMPQ_DESTINATION);
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
