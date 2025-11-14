package duke.userinterface;

/**
 * Represents exceptions that occur in the Duke bot.
 */
public class BotException extends Exception {

    /**
     * Creates a new BotException with the given message.
     *
     * @param input the exception message
     */
    public BotException(String input) {
        super(input);
    }
}
