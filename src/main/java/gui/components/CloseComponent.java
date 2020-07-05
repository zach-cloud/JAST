package gui.components;

import interfaces.IExitProgramService;
import services.ExitProgramServiceGUI;

public final class CloseComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private IExitProgramService exitProgramService;

    public CloseComponent(ComponentContext context, StatusComponent statusComponent) {
        super(context);
        this.exitProgramService = new ExitProgramServiceGUI();
        this.statusComponent = statusComponent;
    }

    public void close() {
        statusComponent.changeStatus("Exiting program");
        exitProgramService.exit();
    }
}
