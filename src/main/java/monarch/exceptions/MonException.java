package monarch.exceptions;

/**
 * Represents an error that occurs in the chatbot.
 */
public class MonException extends RuntimeException {
    /**
     * Constructor for the error.
     *
     * @param errorMessage Error message.
     */
    public MonException(String errorMessage) {
        super(errorMessage);
    }
}
