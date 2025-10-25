package bob.exception;

/**
 * Exception that occurs during runtime
 */
public class InvalidEventUsageException extends RuntimeException {

    public InvalidEventUsageException(String message) {
        super(message);
    }
}
