package exception;

/**
 * Thrown if syntax checking fails
 */
public final class SyntaxErrorException extends RuntimeException {

    public SyntaxErrorException(Exception e) {
        super(e);
    }

    public SyntaxErrorException(String message) {
        super(message);
    }

    public SyntaxErrorException() {
        super("");
    }
}
