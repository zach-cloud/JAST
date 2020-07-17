package gui.components;

import interfaces.IRawcodeService;
import services.RawcodeService;

public final class RawcodeComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private FileComponent fileComponent;
    private IRawcodeService rawcodeService;

    public RawcodeComponent(ComponentContext context, StatusComponent statusComponent) {
        super(context);
        this.statusComponent = statusComponent;
        this.rawcodeService = new RawcodeService();
    }

    public void generateRawcodes() {
        try {
            long time = System.currentTimeMillis();
            rawcodeService = new RawcodeService();
            if (context.objectsFilePath != null) {
                if (context.stringsFilePath != null) {
                    rawcodeService.addWTS(context.stringsFilePath);
                }
                context.jassCodeEditor.replaceText(rawcodeService.makeRawcodesFrom(context.objectsFilePath));
            }
            time = System.currentTimeMillis() - time;
            statusComponent.changeStatus("Generated rawcodes", time);
        } catch (Exception ex) {
            ex.printStackTrace();
            statusComponent.changeStatus("Failed to generate rawcodes.");
        }
    }
}
