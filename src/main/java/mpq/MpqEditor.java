package mpq;

import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.JMpqException;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper to import/export files to the MPQ.
 */
public class MpqEditor {

    private JMpqEditor mpqEditor;
    private static final String[] FILES = {"war3map.j", "scripts\\war3map.j", "war3map.wts", "war3map.w3u", "war3map.w3t", "war3map.w3a"};

    /**
     * Creates a new MPQ Editor class.
     *
     * @param file  MPQ Editor
     */
    public MpqEditor(File file) {
        try {
            mpqEditor = new JMpqEditor(file, MPQOpenOption.FORCE_V0);
        } catch (IOException ex) {
            throw new RuntimeException("Could not open MPQ. It is either corrupt or non-existent.");
        }
    }

    /**
     * Test constructor.
     *
     * @param editor    MPQ Editor
     */
    protected MpqEditor(JMpqEditor editor) {
        this.mpqEditor = editor;
    }

    /**
     * Finds all files under a folder recursively.
     *
     * @param start         Folder to start at
     * @param foldersList   Empty folder list to store results in
     * @param filesList     Empty files ist to store results in
     */
    private static void recursiveFolderDiscovery(File start, List<File> foldersList, List<File> filesList) {
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
     * Extracts all required files into the targeted directory
     *
     * @param destinationFolder Desired directory
     */
    public void extractFiles(File destinationFolder) {
        if(!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        if(!destinationFolder.isDirectory()) {
            throw new RuntimeException("Not a directory: " + destinationFolder.getName());
        }
        for(String file : FILES) {
            if(mpqEditor.hasFile(file)) {
                System.out.println("Attempting to extract: " + file);
                try {
                    File destFile = new File(destinationFolder.getAbsolutePath() + "\\" + file);
                    if(destFile.exists()) {
                        destFile.delete();
                    }
                    destFile.mkdirs();
                    destFile.delete();
                    mpqEditor.extractFile(file, destFile);
                    System.out.println("Successfully extracted " + file + " to " + destinationFolder.getAbsolutePath() + "\\" + file);
                } catch (JMpqException ex) {
                    System.out.println("Unable to extract " + file + " to " + destinationFolder.getAbsolutePath() + "\\" + file);
                }
            } else {
                System.out.println("File not found: " + file);
            }
        }
    }

    /**
     * Returns the filename to import as
     *
     * @param file      File to import
     * @param directory Directory that file is in
     * @return          Filename to import as
     */
    private String getName(File file, File directory) {
        return file.getAbsolutePath().replace(directory.getAbsolutePath() + "\\", "");
    }

    /**
     * Imports all files from the directory into the MPQ Archive
     * @param directory
     */
    public void packFiles(File directory) {
        List<File> files = new ArrayList<>();
        List<File> directories = new ArrayList<>();
        recursiveFolderDiscovery(directory, directories, files);
        //mpqEditor.setExternalListfile(new File("listfile.txt"));
        for(File file : files) {
            if (!file.getName().equals("README.txt") && file.exists() && !file.isDirectory()) {
                String filename = getName(file, directory);
                try {
                    if(mpqEditor.hasFile(filename)) {
                        mpqEditor.deleteFile(filename);
                    }
                    mpqEditor.insertFile(filename, file, false);
                    System.out.println("Inserted file: " + filename);
                } catch (IOException ex) {
                    System.out.println("Unable to insert file: " + filename);
                }
            }
        }
        try {
            mpqEditor.close(true, false, false);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Could not close MPQ: " + ex.getMessage());
        }
    }
}
