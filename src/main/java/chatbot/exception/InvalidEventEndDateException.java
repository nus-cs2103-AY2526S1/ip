package chatbot.exception;

/**
 * InvalidEventEndDatException extends BotException class mainly used for event tasks.
 * This exception is thrown when datetime given in /from occurs after /to
 */
public class InvalidEventEndDateException extends BotException {
    public InvalidEventEndDateException(String msg) {
        super(msg);
    }
}
