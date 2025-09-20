package ming.exception;

/**
 * Represents a custom exception for the Ming application.
 * It is used to handle errors specific to the application's operations.
 */
public class MingException extends Exception {
    private final String message;

    public MingException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ming.exception.MingException: " + message;
    }

    public String getMessage() {
        return message;
    }
}
