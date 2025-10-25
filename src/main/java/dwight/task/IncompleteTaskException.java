package dwight.task;

/**
 * Exception thrown when a task cannot be created or parsed due to missing or incomplete
 * information.
 */
public class IncompleteTaskException extends Exception {

    /**
     * Creates a new {@code IncompleteTaskException} with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public IncompleteTaskException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code IncompleteTaskException} with the specified detail message and
     * underlying cause.
     *
     * @param message The detail message explaining the cause of the exception.
     * @param cause The underlying cause of the exception.
     */
    public IncompleteTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
