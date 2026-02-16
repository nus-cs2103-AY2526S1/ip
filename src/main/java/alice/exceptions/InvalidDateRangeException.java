package alice.exceptions;

/**
 * Thrown when a start date/time is not strictly before an end date/time.
 */
public class InvalidDateRangeException extends AliceException {
    public InvalidDateRangeException(String message) {
        super(message);
    }
}
