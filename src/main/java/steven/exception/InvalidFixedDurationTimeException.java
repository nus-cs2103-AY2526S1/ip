package steven.exception;

/**
 * Thrown when the user attempts to create a fixed duration tasks but the duration is not a number.
 */
public class InvalidFixedDurationTimeException extends StevenException {
    public InvalidFixedDurationTimeException() {
        super("\tPls use a number for the number of hours");
    }
}
