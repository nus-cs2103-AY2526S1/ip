package chatbot.exception;

/**
 * InvalidArgumentException extends BotException class and handles when user inputs are invalid.
 */
public class InvalidArgumentException extends BotException {
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
