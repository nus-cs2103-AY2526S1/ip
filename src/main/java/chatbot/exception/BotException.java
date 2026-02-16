package chatbot.exception;

/**
 * BotException extends Exception class and prompts user of invalid commands or parsing errors.
 */
public class BotException extends Exception {
    public BotException(String msg) {
        super(msg);
    }
}
