package exception;

/**
 * Thrown if a code merge crashes
 */
public final class MergeFailureException extends RuntimeException {

    public MergeFailureException(Exception e) {
        super(e);
    }

    public MergeFailureException(String message) {
        super(message);
    }

    public MergeFailureException() {
        super("");
    }
}
