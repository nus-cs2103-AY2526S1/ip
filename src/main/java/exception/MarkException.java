package exception;

/**
 * A runtime exception for errors that can occur when trying to mark tasks
 * in a {@link task.TaskList} as done/undone.
 */
public class MarkException extends RuntimeException {
    /**
     * @param message the provided error message
     */
    public MarkException(String message) {
        super(message);
    }
}
