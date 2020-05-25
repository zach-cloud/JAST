package helper;

import java.io.File;
import java.util.List;

public class FileHelper {

    /**
     * Finds all files under a folder recursively.
     *
     * @param start         Folder to start at
     * @param foldersList   Empty folder list to store results in
     * @param filesList     Empty files ist to store results in
     */
    public static void recursiveFolderDiscovery(File start, List<File> foldersList, List<File> filesList) {
        if (start.isDirectory()) {
            foldersList.add(start);
            File[] files = start.listFiles();
            if (files != null) {
                for (File file : files) {
                    recursiveFolderDiscovery(file, foldersList, filesList);
                }
            }
        } else {
            filesList.add(start);
        }
    }
}
