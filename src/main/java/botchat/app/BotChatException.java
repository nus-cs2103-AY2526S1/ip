package botchat.app;

/**
 * An exception when user input is invalid.
 */
public class BotChatException extends Exception {
    /**
     * Constructs the exception.
     * @param message details the error.
     */
    public BotChatException(String message) {
        super(message);
    }
}
