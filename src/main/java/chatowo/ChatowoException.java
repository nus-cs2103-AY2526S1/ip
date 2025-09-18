package chatowo;

/**
 * Custom exception for Chatowo-specific errors.
 * Used to handle and display user-friendly error messages.
 */
public class ChatowoException extends Exception {
    /**
     * Creates a new ChatowoException with specified message.
     *
     * @param msg Error message to display to user
     */
    public ChatowoException(String msg) {
        super(msg);
    }
}
