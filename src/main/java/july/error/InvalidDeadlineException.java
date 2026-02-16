package july.error;

/**
 * Exception thrown when a deadline task cannot be created due to invalid input format.
 * This exception is used to indicate problems with deadline task creation,
 * such as invalid date formats, missing required components, or malformed input.
 */
public class InvalidDeadlineException extends Exception {

    /**
     * Constructs an InvalidDeadlineException with the specified error message.
     *
     * @param message the detail message explaining why the deadline is invalid
     */
    public InvalidDeadlineException(String message) {
        super(message);
    }
}
