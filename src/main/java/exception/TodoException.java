package exception;

/**
 * A runtime exception for errors that can occur when creating {@link task.ToDo} objects.
 */
public class TodoException extends RuntimeException {
    /**
     * @param message the provided error message
     */
    public TodoException(String message) {
        super(message);
    }
}
