package aurora.task;

/**
 * Signals that an invalid task was encountered.
 * <p>
 * This exception is thrown when a task cannot be created
 * due to invalid input or format.
 */
public class InvalidTaskException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidTaskException} with the specified message.
     *
     * @param message the reason for the exception
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
