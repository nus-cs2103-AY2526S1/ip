package jimbot.exception;

/**
 * Represents an InvalidInput exception that occurs when the user inputs an unknown command.
 */
public class InvalidInputException extends JimbotException {
    /**
     * Constructs an InvalidInput exception with the specified error message.
     */
    public InvalidInputException(String userInput) {
        super("Oops I don't recognize this command:\n"
                + "  \"" + userInput + "\"\n"
                + "Type \"help\" for the list possible commands.");
    }
}
