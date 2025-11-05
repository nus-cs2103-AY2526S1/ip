package sunoo.exception;

/**
 * Represents an exception to be thrown when commands or user inputs are problematic.
 */
public class SunooException extends RuntimeException {

    /**
     * Creates a SunooException with a message to be shown to the user.
     *
     * @param message Message to be shown.
     */
    public SunooException(String message) {
        super(message);
    }
}
