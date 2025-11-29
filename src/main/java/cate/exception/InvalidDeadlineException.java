package cate.exception;

/**
 * Exception thrown when a {@code deadline} command is missing the required
 * {@code /by} keyword or contains an invalid date/time format in the Cate application.
 * <p>
 * This exception is typically thrown during parsing of user input if the
 * deadline specification is incomplete or incorrectly formatted.
 * </p>
 */
public class InvalidDeadlineException extends CateException {

    /**
     * Constructs an {@code InvalidDeadlineException} with a default message
     * indicating the missing or invalid deadline specification.
     */
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
