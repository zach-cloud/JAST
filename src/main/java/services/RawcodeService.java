package services;

import interfaces.IRawcodeService;
import nodes.wts.WtsString;
import nodes.wts.WtsStringsFile;
import rawcode.*;
import tree.TreeContext;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Helper service to generate clean rawcode strings based on rawcode objects
 */
public class RawcodeService implements IRawcodeService {

    private WtsStringsFile wtsFile;

    /**
     * Adds a WTS File resource to replace Strings with
     *
     * @param wtsFile WTS File
     */
    @Override
    public void addWTS(String wtsFile) {
        try {
            this.wtsFile = new WtsStringsFile(new Scanner(new File(wtsFile)), new TreeContext());
        } catch (IOException ex) {
            throw new RuntimeException("Could not load wts file: " + wtsFile);
        }
    }

    private static String clean(String text) {
        // strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", "");

        // erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", "");

        return text.trim();
    }

    private String replace(String name) {
        if(wtsFile == null) {
            return name;
        }
        if(name.startsWith("TRIGSTR_")) {
            try {
                int key = Integer.parseInt(name.replace("TRIGSTR_", "").trim());
                for (WtsString wtsString : wtsFile.getStrings()) {
                    String convertedKey = wtsString.getKey().replace("STRING ", "").replace("\"", "").trim();
                    convertedKey = clean(convertedKey);
                    int foundKey = Integer.parseInt(convertedKey);
                    if (key == foundKey) {
                        return wtsString.getValue().replace("\n", " ");
                    }
                }
            } catch (NumberFormatException ex) {
                // Some sort of malformed key. Who cares?
                return name;
            }
        }
        return name;
    }

    /**
     * Creates a Rawcodes text string based on an Objects Structure
     *
     * @param objects W3 Objects File
     * @return Rawcodes text string
     */
    @Override
    public String makeRawcodesFrom(ObjectsStructure objects) {
        StringBuilder rawcodes = new StringBuilder();
        for(ModifiedObject modifiedObject : objects.getModifiedObjects()) {
            String name = "(no name provided)";
            for(ModifiedObjectField field : modifiedObject.getFields()) {
                if(field.getFieldCode().equals("unam")) {
                    name = replace(field.getFieldData().trim());
                }
            }
            rawcodes.append("[").append(modifiedObject.getRawcode()).append("] ").append(name).append("\n");
        }
        for(NewObject newObject : objects.getNewObjects()) {
            String name = "(no name provided)";
            for(NewObjectField field : newObject.getFields()) {
                if(field.getFieldCode().equals("unam")) {
                    name = replace(field.getFieldData().trim());
                }
            }
            rawcodes.append("[").append(newObject.getRawcode()).append("] ").append(name).append("\n");
        }
        if(rawcodes.length() > 0) {
            rawcodes.setLength(rawcodes.length()-1);
        }
        return rawcodes.toString();
    }

    /**
     * Creates a Rawcodes text string based on a file pointing to an Objects structure
     *
     * @param filePath File path
     * @return Rawcodes text string
     */
    @Override
    public String makeRawcodesFrom(String filePath) {
        return makeRawcodesFrom(new RawcodeBinaryReader().readObject(new File(filePath)));
    }
}
