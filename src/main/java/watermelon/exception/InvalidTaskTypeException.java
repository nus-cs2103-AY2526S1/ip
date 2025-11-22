package watermelon.exception;

/**
 * An exception that is thrown when a task type is invalid.
 */
public class InvalidTaskTypeException extends WatermelonException {
    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     */
    public InvalidTaskTypeException() {
        super("invalid task type!");
    }
}
