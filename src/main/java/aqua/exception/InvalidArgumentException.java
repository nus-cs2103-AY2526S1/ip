package aqua.exception;

/**
 * Exception thrown when an invalid argument is provided to a method or function.
 */
public class InvalidArgumentException extends AquaException {
    /**
     * Constructor for InvalidArgumentException
     *
     * @param message Error message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
