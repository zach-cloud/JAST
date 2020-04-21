package exception;

/**
 * Thrown is a variable/function rename crashes
 */
public final class RenameFailureException extends RuntimeException {

    public RenameFailureException(Exception e) {
        super(e);
    }

    public RenameFailureException(String message) {
        super(message);
    }

    public RenameFailureException() {
        super("");
    }
}
