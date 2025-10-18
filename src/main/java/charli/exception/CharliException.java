package charli.exception;


/**
 * Custom exception class for Charli chatbot-specific errors.
 * Used to handle and communicate application-specific error conditions.
 */
public class CharliException extends Exception {
    /**
     * Constructs a new CharliException with the specified detail message.
     *
     * @param message the detail message explaining the error
     */
    public CharliException(String message) {
        super(message);
    }
}
