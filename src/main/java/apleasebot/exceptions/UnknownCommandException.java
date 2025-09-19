package apleasebot.exceptions;

/**
 * Exception thrown when the user inputs something that is not recognised by the parser
 */
public class UnknownCommandException extends APleaseBotException {
    /**
     * Constructor for the UnknownCommandException to format the error message
     * @param message Takes in an additional display message specific to the error
     */
    public UnknownCommandException(String message) {
        super("I am not sure what you mean by: " + "\"" + message + "\"" + "\n"
                + "Use 'help' to see list of recognised words and arguments needed");
    }
}
