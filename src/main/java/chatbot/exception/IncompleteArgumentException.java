package chatbot.exception;

/**
 * IncompleteArgumentException extends BotException class and handles when user inputs are incomplete.
 */
public class IncompleteArgumentException extends BotException {
    public IncompleteArgumentException(String msg) {
        super(msg);
    }
}
