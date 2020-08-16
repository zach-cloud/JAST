package template;

import helper.FileHelper;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to load included templates in the project
 */
public final class TemplateLoader {

    private static final String TEMPLATES_CONTENT_PATH = "templates/content/";
    private static final String TEMPLATES_METADATA_PATH = "templates/metadata/";

    /**
     * Loads template or metadata
     *
     * @param basePath  Path to read from
     * @param name      Name to read
     * @return          File data as String
     */
    private static String loadGeneric(String basePath, String name) {
        name = name.replace(".j", "");
        // Attempt loading template from file
        File externalWithWithJ = new File(basePath + name + ".j");
        if(externalWithWithJ.exists()) {
            try {
                System.out.println("Loading external templates: " + externalWithWithJ.getName());
                return FileUtils.readFileToString(externalWithWithJ, Charset.defaultCharset());
            } catch (IOException ex) {
                System.out.println("Attempted to read: " + externalWithWithJ.getName() + " but read failed.");
            }
        }
        File externalFile = new File(basePath + name);
        if(externalFile.exists()) {
            try {
                System.out.println("Loading external templates: " + externalFile.getName());
                return FileUtils.readFileToString(externalFile, Charset.defaultCharset());
            } catch (IOException ex) {
                System.out.println("Attempted to read: " + externalFile.getName() + " but read failed.");
            }
        }
        throw new IllegalArgumentException("Unrecognized template: " + name);
    }

    /**
     * Loads the contents of a template by the name.
     *
     * @param name  Name of template to load
     * @return      Contents of template
     */
    public static String loadTemplateByName(String name) {
        return loadGeneric(TEMPLATES_CONTENT_PATH, name);
    }

    /**
     * Loads the metadata of a template by the name.
     *
     * @param name  Name of template metadata to load
     * @return      Metadata of template
     */
    public static TemplateMetadata loadTemplateMetadataByName(String name) {
        TemplateMetadata metadata = new TemplateMetadata();
        String metadataValue = loadGeneric(TEMPLATES_METADATA_PATH, name);
        JSONObject jsonObject = new JSONObject(metadataValue);
        if(jsonObject.has("options")) {
            JSONArray optionsArray = jsonObject.getJSONArray("options");
            for(int i = 0; i < optionsArray.length(); i++) {
                JSONObject fieldObject = optionsArray.getJSONObject(i);
                if(fieldObject.has("fieldName") &&
                        fieldObject.has("type") &&
                        fieldObject.has("default")) {
                    TemplateField field = new TemplateField();
                    field.setFieldName(fieldObject.getString("fieldName"));
                    field.setType(fieldObject.getString("type"));
                    field.setDefaultValue(fieldObject.getString("default"));
                    metadata.add(field);
                }

            }
        }
        return metadata;
    }

    private static boolean canLoadGeneric(String basePath, String name) {
        name = name.replace(".j", "");
        boolean isExternalTemplate = new File(basePath + name).exists();
        boolean isExternalTemplateWithJ = new File(basePath + name + ".j").exists();
        return isExternalTemplate || isExternalTemplateWithJ;
    }

    /**
     * Determines whether or not this template was packaged
     * up with the program.
     *
     * @param name  Name of template to load
     * @return      True if template exists; false if not.
     */
    public static boolean canLoadTemplateByName(String name) {
        return canLoadGeneric(TEMPLATES_CONTENT_PATH, name);
    }

    /**
     * Determines whether or not this template metadata was packaged
     * up with the program.
     *
     * @param name  Name of template metadata to load
     * @return      True if template metadata exists; false if not.
     */
    public static boolean canLoadTemplateMetadataByName(String name) {
        return canLoadGeneric(TEMPLATES_METADATA_PATH, name);
    }

    /**
     * Retrieves a list of all available templates.
     *
     * @return  Available templates
     */
    public static List<String> getAllTemplates() {
        File templatesPath = new File(TEMPLATES_CONTENT_PATH);
        List<File> allFiles = new ArrayList<>();
        FileHelper.recursiveFolderDiscovery(templatesPath, new ArrayList<>(), allFiles);
        List<String> allTemplates = new ArrayList<>();
        for(File file : allFiles) {
            allTemplates.add(file.getName());
        }
        return allTemplates;
    }
}
