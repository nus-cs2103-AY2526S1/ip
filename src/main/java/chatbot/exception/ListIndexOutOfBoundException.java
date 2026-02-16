package chatbot.exception;

/**
 * InvalidArgumentException extends BotException class.
 * This exception is thrown when users try to mark, unmark or delete tasks that do not exist.
 */
public class ListIndexOutOfBoundException extends BotException {
    public ListIndexOutOfBoundException(String msg) {
        super(msg);
    }
}
