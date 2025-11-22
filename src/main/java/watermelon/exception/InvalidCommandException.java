package watermelon.exception;

/**
 * An exception that is thrown when a command is invalid.
 */
public class InvalidCommandException extends WatermelonException {
    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     */
    public InvalidCommandException() {
        super("invalid command!");
    }
}
