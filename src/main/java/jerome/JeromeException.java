package jerome;
/**
 * Represents a custom exception for the Jerome chatbot.
 * Used to indicate errors related to invalid user inputs.
 */
public class JeromeException extends Exception{
    /**
     * Constructs a new <code>JeromeExceotion</code> with the relevant error message.
     *
     * @param message Error message to be shown.
     */
    public JeromeException(String message) {
        super(message);
    }
}
