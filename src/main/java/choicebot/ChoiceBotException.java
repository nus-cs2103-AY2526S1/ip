package choicebot;

/**
 * Represents an exception specific to ChoiceBot.
 * The exception is thrown when an error occurs during the running of ChoiceBot, such as invalid user input.
 */
public class ChoiceBotException extends Exception {
    /**
     * Constructs a ChoiceBotException with the specified message explaining the error causing the exception.
     */
    public ChoiceBotException(String message) {
        super(message);
    }
}
