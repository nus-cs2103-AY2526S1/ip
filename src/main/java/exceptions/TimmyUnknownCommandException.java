package exceptions;

public class TimmyUnknownCommandException extends Exception {
    public TimmyUnknownCommandException() {}

    public TimmyUnknownCommandException(String message) {
        super(message);
    }
}
