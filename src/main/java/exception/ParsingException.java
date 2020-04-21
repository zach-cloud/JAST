package exception;

/**
 * Thrown if the AST Reading code fails to deserialize the JASS code
 */
public final class ParsingException extends RuntimeException {

    public ParsingException(Exception e) {
        super(e);
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException() {
        super("");
    }
}
