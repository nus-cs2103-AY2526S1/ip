package aqua.exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends AquaException {
    /**
     * Constructor for InvalidCommandException
     *
     * @param message Error message
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
