package gui.components;

import mpq.MpqEditor;

import java.io.File;

public final class MpqComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private MpqEditor mpqEditor;

    public MpqComponent(ComponentContext context, StatusComponent statusComponent) {
        super(context);
        this.statusComponent = statusComponent;
    }

    /**
     * Extracts MPQ files
     */
    public void extractMpq(String mpqPath, String projectName) {
        try {
            long time = System.currentTimeMillis();
            if (mpqPath != null) {
                File file = new File("projects/" + projectName + "/");
                if (file.exists()) {
                    file.delete();
                }
                file.mkdirs();
                file.delete();
                mpqEditor = new MpqEditor(new File(mpqPath));
                mpqEditor.extractFiles(file);
            }
            time = System.currentTimeMillis() - time;
            statusComponent.changeStatus("Extracted to /projects" + projectName + "/", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to extract files.");
        }
    }

    public void saveMpq(File mpqPath, File selectedFile, String currentProject) {
        this.mpqEditor = new MpqEditor(mpqPath);
        mpqEditor.packFiles(new File("projects/" + currentProject), selectedFile);
    }
}
