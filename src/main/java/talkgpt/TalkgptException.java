package talkgpt;

/**
 * Custom exception class for the TalkGPT application.
 * Used to represent application-specific errors and provide meaningful error messages.
 */
public class TalkgptException extends Exception {
    /**
     * Constructs a new TalkGPTException with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public TalkgptException(String message) {
        super(message);
    }
}
