package beebong.exception;

/**
 * Represents an Invalid Task Details exception used within the BeeBong application.
 */
public class InvalidTaskDetailsException extends BBongException {
    /**
     * Constructs a new {@code InvalidTaskDetailsException} with a pre-defined message.
     */
    public InvalidTaskDetailsException() {
        super("Invalid Task Details!");
    }

    /**
     * Constructs a new {@code InvalidTaskDetailsException} with the specified detail message.
     *
     * @param message the detail message to describe the cause of the exception.
     */
    public InvalidTaskDetailsException(String message) {
        super(message);
    }
}
