package david.exception;

/**
 * Initialises an Exception that will be thrown from invalid date and time formats.
 */
public class InvalidDateTimeException extends DavidException{
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
