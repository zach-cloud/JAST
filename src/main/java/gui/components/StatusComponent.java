package gui.components;

public class StatusComponent extends GenericComponent {

    public StatusComponent(ComponentContext context) {
        super(context);
    }

    /**
     * Changes the current status
     *
     * @param status Desired status
     */
    public void changeStatus(String status) {
        context.statusLabel.setText(status);
    }

    /**
     * Changes the current status
     *
     * @param status Desired status
     * @param time   Time taken to finish the last action
     */
    public void changeStatus(String status, long time) {
        context.statusLabel.setText(status + " (" + time + " ms)");
    }

}
