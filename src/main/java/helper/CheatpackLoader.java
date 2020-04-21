package helper;

import exception.ParsingException;
import org.apache.commons.io.FileUtils;

import java.io.File;
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
        return name.equals("FAI") || name.equals("JJCP") || name.equals("NZCP");
    }

}
