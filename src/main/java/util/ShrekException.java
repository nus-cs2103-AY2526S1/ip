package util;

/**
 * Custom exception class for Shrek application-specific errors.
 */
public class ShrekException extends Exception {
    /**
     * Constructs a new ShrekException with the specified detail message.
     *
     * @param message the detail message explaining the error
     */
    public ShrekException(String message) {
        super(message);
    }
}
