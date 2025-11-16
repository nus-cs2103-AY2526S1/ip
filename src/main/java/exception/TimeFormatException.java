package exception;

/**
 * Exception thrown when a time format is invalid or cannot be parsed.
 */
public class TimeFormatException extends Exception {
    public TimeFormatException(String message) {
        super(message);
    }
}
