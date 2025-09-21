package exception;

/**
 * A runtime exception for errors that can occur when creating {@link task.Event} objects.
 */
public class EventException extends RuntimeException {
    /**
     * @param message the provided error message
     */
    public EventException(String message) {
        super(message);
    }
}
