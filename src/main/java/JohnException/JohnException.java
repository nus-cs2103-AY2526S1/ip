package JohnException;

/**
 * Exception to handle all errors caused by invalid user inputs.
 */
public class JohnException extends Exception {
    /**
     * Creates a JohnException with the given message.
     *
     * @param message Human-readable error description.
     */
    public JohnException(String message) {
        super(message);
    }
}
