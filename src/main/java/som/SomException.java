package som;

/**
 * Custom exception class for user-facing errors in the Som chatbot.
 * <p> Used to wrap and report invalid inputs, file errors, and other
 * recoverable issues in a consistent way.</p>
 *
 * @author Darien Tan
 */
public class SomException extends Exception {
    /**
     * Constructs a new SomException with the specified message.
     *
     * @param message the error message.
     */
    public SomException(String message) {
        super(message);
    }
}
