package helper;

import java.io.File;
import java.util.List;

public final class FileHelper {

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

    /**
     * Deletes directory and all files in it.
     *
     * @param directoryToBeDeleted  Directory to delete
     * @return                      True if success; false if failure.
     */
    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}
