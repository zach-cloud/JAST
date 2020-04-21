package exception;

/**
 * Thrown if writing to file crashes
 */
public final class WritingException extends RuntimeException {

    public WritingException(Exception e) {
        super(e);
    }

    public WritingException(String message) {
        super(message);
    }

    public WritingException() {
        super("");
    }
}
