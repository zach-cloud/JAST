package mpq;

import constants.Constants;
import helper.FileHelper;
import helper.InterfaceHelper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper to import/export files to the MPQ.
 */
public class MpqEditor {

    private File file;
    private static final String[] FILES = {"war3map.j", "scripts\\war3map.j", "war3map.wts", "war3map.w3u", "war3map.w3t", "war3map.w3a"};

    /**
     * Creates a new MPQ Editor class.
     *
     * @param file MPQ Editor
     */
    public MpqEditor(File file) {
        this.file = file;
    }

    private void copyFileToMpqDir() {
        try {
            FileUtils.copyFile(this.file, new File(Constants.FROZENMPQ_FOLDER + "myMap.w3x"));
            System.out.println("Copied file to mpq directory");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Extracts all required files into the targeted directory
     *
     * @param destinationFolder Desired directory
     */
    public void extractFiles(File destinationFolder) {
        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }
        if (!destinationFolder.isDirectory()) {
            throw new RuntimeException("Not a directory: " + destinationFolder.getName());
        }
        File output = new File(Constants.FROZENMPQ_OUTPUT);
        if(output.exists()) {
            if(!output.isDirectory()) {
                output.delete();
            } else {
                FileHelper.deleteDirectory(output);
            }
        }
        copyFileToMpqDir();
        StringBuilder fileCommand = new StringBuilder();
        for (String file : FILES) {
            fileCommand.append("\"")
                    .append(file).append("\" ");
        }
        fileCommand.setLength(fileCommand.length()-1);
        System.out.println(
                executeFrozenMpq("\"ext\"",
                        "\"" + Constants.USER_PATH +
                                Constants.FROZENMPQ_FOLDER +
                                "myMap.w3x\"", fileCommand.toString()));
        try {
            FileUtils.copyDirectory(output, destinationFolder);
            FileUtils.deleteDirectory(output);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }

    /**
     * Imports all files from the directory into the MPQ Archive
     *
     * @param directory Directory containing files to import
     */
    public void packFiles(File directory) {
        List<File> files = new ArrayList<>();
        List<File> directories = new ArrayList<>();
        File input = new File(Constants.FROZENMPQ_INPUT);
        if(input.exists()) {
            if(!input.isDirectory()) {
                input.delete();
            } else {
                FileHelper.deleteDirectory(input);
            }
        }
        input.mkdirs();
        copyFileToMpqDir();
        try {
            FileUtils.copyDirectory(directory, input);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        FileHelper.recursiveFolderDiscovery(directory, directories, files);
        StringBuilder fileCommand = new StringBuilder();
        for (String file : FILES) {
            fileCommand.append("\"")
                    .append(file).append("\" ");
        }
        fileCommand.setLength(fileCommand.length()-1);

        System.out.println(
                executeFrozenMpq("\"imp\"",
                        "\"" + Constants.USER_PATH +
                                Constants.FROZENMPQ_FOLDER +
                                "myMap.w3x\"", fileCommand.toString()));

        try {
            FileUtils.deleteDirectory(input);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String executeFrozenMpq(String operationType, String filePath, String files) {
        try {
            String command = "\"" + Constants.USER_PATH + Constants.FROZENMPQ_FOLDER + "FrozenMpq.exe\" " + operationType + " " + filePath + " " + files;
            return InterfaceHelper.executeCommand(command);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

}
