package exception;

/**
 * A runtime exception for errors that can occur when creating {@link task.Deadline} objects.
 */
public class DeadlineException extends RuntimeException {
    /**
     * @param message the provided error message
     */
    public DeadlineException(String message) {
        super(message);
    }
}
