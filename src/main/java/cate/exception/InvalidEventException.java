package cate.exception;

/**
 * Exception thrown when an {@code event} command is missing the required
 * {@code /from} or {@code /to} keywords, or contains invalid start/end times
 * in the Cate application.
 * <p>
 * This exception is typically thrown during parsing of user input if the
 * event specification is incomplete or incorrectly formatted.
 * </p>
 */
public class InvalidEventException extends CateException {

    /**
     * Constructs an {@code InvalidEventException} with a default message
     * indicating the missing or invalid event times.
     */
    public InvalidEventException() {
        super("Event requires both /from and /to with valid times.");
    }
}
