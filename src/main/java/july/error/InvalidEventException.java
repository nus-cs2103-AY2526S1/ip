package july.error;

/**
 * Exception thrown when an event task cannot be created due to invalid input format.
 * This exception is used to indicate problems with event task creation,
 * such as invalid date/time formats, missing required components, or malformed input.
 */
public class InvalidEventException extends Exception {

    /**
     * Constructs an InvalidEventException with the specified error message.
     *
     * @param message the detail message explaining why the event is invalid
     */
    public InvalidEventException(String message) {
        super(message);
    }
}
