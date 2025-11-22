package watermelon.exception;

/**
 * An exception that is thrown when date (and time) is inputted in the wrong format.
 */
public class InvalidDateTimeException extends WatermelonException {
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
