package gui.components;

import interfaces.IExitProgramService;
import services.ExitProgramServiceGUI;

/**
 * Component to close the program
 */
public final class CloseComponent extends GenericComponent {

    private StatusComponent statusComponent;
    private IExitProgramService exitProgramService;

    /**
     * Required constructor
     *
     * @param context   Component context
     * @param statusComponent   Status label component
     */
    public CloseComponent(ComponentContext context, StatusComponent statusComponent) {
        super(context);
        this.exitProgramService = new ExitProgramServiceGUI();
        this.statusComponent = statusComponent;
    }

    /**
     * Closes program.
     */
    public void close() {
        statusComponent.changeStatus("Exiting program");
        exitProgramService.exit();
    }
}
