package apleasebot.exceptions;

/**
 * Exception thrown when a command is called correctly
 * but the arguments inputted into the terminal is wrong
 * or missing
 */
public class IllegalBotArgumentException extends APleaseBotException {
    /**
     * Constructor for the IllegalBotArgumentException to generate the message in a specific format
     * @param message Takes in any additional message specific to the function
     * @param input Takes in the input provided by the user to show where the error is
     */
    public IllegalBotArgumentException(String message, String input) {
        super(message + "\n"
                + "Use 'help' to see list of recognised words and arguments needed"
                + "\nYour input:\n"
                + input + "\n");
    }
}
