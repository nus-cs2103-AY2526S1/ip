package bestbot.exception;

/**
 * Exception class to represent application-specific errors in Bestbot.
 */
public class BestbotException extends Exception {
    /**
     * Constructs a BestbotException with the specified message.
     *
     * @param message The error message.
     */
    public BestbotException(String message) {
        super(message);
    }
}
