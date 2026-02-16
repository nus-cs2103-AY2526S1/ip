package guibot.exception;

/**
 * Exception when user inputs DateTime in the wrong format.
 */
public class WrongDateTimeFormatException extends GuibotException {
    /**
     * Creates a WrongDateTimeFormatException.
     */
    public WrongDateTimeFormatException() {
        super("Expected date time format: yyyy-MM-dd HHmm (eg. 2025-09-01 2359)");
    }
}
