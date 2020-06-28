package exception;

public class SyntaxErrorException extends RuntimeException {

    public SyntaxErrorException(String message) {
        super(message);
    }

    public SyntaxErrorException(Exception ex) {
        super(ex);
    }

    public SyntaxErrorException() {
        super("");
    }
}
