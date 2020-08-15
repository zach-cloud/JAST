package mpq;

import constants.Constants;
import frost.FrostMpq;
import helper.FileHelper;
import helper.InterfaceHelper;
import interfaces.IFrostMpq;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import settings.MpqSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper to import/export files to the MPQ.
 */
public class MpqEditor {

    private File file;
    private IFrostMpq frostMpq;
    private static final String[] FILES = {"war3map.j", "scripts\\war3map.j", "war3map.wts", "war3map.w3u", "war3map.w3t", "war3map.w3a"};

    /**
     * Creates a new MPQ Editor class.
     *
     * @param file MPQ Editor
     */
    public MpqEditor(File file) {
        this.file = file;
        MpqSettings settings = new MpqSettings();
        settings.setCompressionSettings(MpqSettings.CompressionSettings.DEFLATE);
        settings.setLogSettings(MpqSettings.LogSettings.INFO);
        settings.setMpqOpenSettings(MpqSettings.MpqOpenSettings.CRITICAL);
        settings.setSecuritySettings(MpqSettings.SecuritySettings.ENCRYPTION_DISABLED);

        frostMpq = new FrostMpq(file);
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
        if (output.exists()) {
            if (!output.isDirectory()) {
                output.delete();
            } else {
                FileHelper.deleteDirectory(output);
            }
        }


        for (String fileName : FILES) {
            try {
                if (frostMpq.fileExists(fileName)) {
                    System.out.println("Extracting: " + fileName);
                    frostMpq.extractFile(fileName);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }


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
    public void packFiles(File directory, File destination) {
        File input = new File(Constants.FROZENMPQ_INPUT);
        if (input.exists()) {
            if (!input.isDirectory()) {
                input.delete();
            } else {
                FileHelper.deleteDirectory(input);
            }
        }
        input.mkdirs();
        try {
            FileUtils.copyDirectory(directory, input);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            for (String fileName : FILES) {
                File desiredFile = new File(fileName);
                if (desiredFile.exists()) {
                    frostMpq.importFile(fileName, IOUtils.toByteArray(new FileInputStream(desiredFile)));
                }
            }

            frostMpq.save(destination);

            FileUtils.deleteDirectory(input);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
