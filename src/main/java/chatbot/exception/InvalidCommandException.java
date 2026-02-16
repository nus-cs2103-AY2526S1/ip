package chatbot.exception;

/**
 * InvalidCommandException extends BotException class and handles when user calls and unknown command.
 */
public class InvalidCommandException extends BotException {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
