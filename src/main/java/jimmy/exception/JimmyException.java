package jimmy.exception;

/**
 * Represents an exception for the Jimmy chatbot.
 */
public class JimmyException extends Exception {

    /**
     * Constructs a JimmyException.
     *
     * @param message Error message to be shown.
     */
    public JimmyException(String message) {
        super(message);
    }
}
