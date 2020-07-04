package gui.components;

import mpq.MpqEditor;

import java.io.File;

public final class MpqComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private FileComponent fileComponent;
    private MpqEditor mpqEditor;

    public MpqComponent(ComponentContext context, StatusComponent statusComponent, FileComponent fileComponent) {
        super(context);
        this.statusComponent = statusComponent;
        this.fileComponent = fileComponent;
    }

    /**
     * Extracts MPQ files
     */
    public void extractMpq() {
        try {
            long time = System.currentTimeMillis();
            if (context.mpqPath == null) {
                fileComponent.open();
            }
            if (context.mpqPath != null) {
                File file = new File("tempFiles/");
                if (file.exists()) {
                    file.delete();
                }
                file.mkdirs();
                file.delete();
                mpqEditor = new MpqEditor(new File(context.mpqPath));
                mpqEditor.extractFiles(file);
            }
            time = System.currentTimeMillis() - time;
            statusComponent.changeStatus("Extracted to /tempFiles", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to extract files.");
        }
    }
}
