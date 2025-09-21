package exception;

/**
 * A runtime exception for errors that can occur when trying to delete tasks
 * from a {@link task.TaskList}.
 */
public class DeleteException extends RuntimeException {
    /**
     * @param message the provided error message
     */
    public DeleteException(String message) {
        super(message);
    }
}
