package pengu.exception;

/**
 * Exception thrown when an invalid command is received.
 */
public class InvalidCommandException extends PenguException {
    /**
     * Constructor for the exception.
     */
    public InvalidCommandException() {
        super("Invalid command received :(");
    }
}
