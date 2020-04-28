package helper;

import exception.ParsingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Helper class to load included packages in the project
 * Currently contains NZCP, JJCP, and FAI.
 */
public final class CheatpackLoader {

    /**
     * Loads the contents of a cheatpack by the name.
     *
     * @param name  Name of cheatpack to load
     * @return      Contents of cheatpack
     */
    public static String loadCheatpackByName(String name) {
        name = name.replace(".j", "");
        // Attempt loading cheatpack from file
        File externalWithWithJ = new File("cheatpacks/" + name + ".j");
        if(externalWithWithJ.exists()) {
            try {
                System.out.println("Loading external cheatpack: " + externalWithWithJ.getName());
                return FileUtils.readFileToString(externalWithWithJ, Charset.defaultCharset());
            } catch (IOException ex) {
                System.out.println("Attempted to read: " + externalWithWithJ.getName() + " but read failed.");
            }
        }
        File externalFile = new File("cheatpacks/" + name);
        if(externalFile.exists()) {
            try {
                System.out.println("Loading external cheatpack: " + externalFile.getName());
                return FileUtils.readFileToString(externalFile, Charset.defaultCharset());
            } catch (IOException ex) {
                System.out.println("Attempted to read: " + externalFile.getName() + " but read failed.");
            }
        }
        System.out.println("Loading internal cheatpack...");
        if(name.equals("FAI")) {
            return FAI.CHEATPACK;
        } else if(name.equals("JJCP")) {
            return JJCP.CHEATPACK;
        } else if(name.equals("NZCP")) {
            return NZCP.CHEATPACK;
        } else {
            throw new IllegalArgumentException("Unrecognized cheatpack: " + name);
        }
    }

    /**
     * Determines whether or not this cheatpack was packaged
     * up with the program.
     *
     * @param name  Name of cheatpack to load
     * @return      True if cheatpack exists; false if not.
     */
    public static boolean canLoadCheatpackByName(String name) {
        name = name.replace(".j", "");
        boolean isInternalCheatpack = name.equals("FAI") || name.equals("JJCP") || name.equals("NZCP");
        boolean isExternalCheatpack = new File("cheatpacks/" + name).exists();
        boolean isExternalCheatpackWithJ = new File("cheatpacks/" + name + ".j").exists();
        return isInternalCheatpack || isExternalCheatpack || isExternalCheatpackWithJ;
    }

}
