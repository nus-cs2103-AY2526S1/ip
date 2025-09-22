package izayoi.exception;

/**
 * Represents an exception caused by providing data in an invalid format
 */
public class InvalidFormatException extends IzayoiException {
    /**
     * Initializes a new InvalidFormatException with a complaint
     * @param message the message to display, preferably sassier than usual
     */
    public InvalidFormatException(String message) {
        super("This is ridiculous... " + message);
    }
}
