package shef.exception;

/**
 * Indicates commands being supplied with invalid arguments.
 */
public class InvalidArgumentException extends ShefException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
