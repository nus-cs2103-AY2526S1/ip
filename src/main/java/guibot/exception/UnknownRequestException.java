package guibot.exception;

/**
 * Exception when user inputs an unknown request.
 */
public class UnknownRequestException extends GuibotException {
    /**
     * Creates an UnknownRequestException.
     */
    public UnknownRequestException() {
        super("Sorry, I do not know what you want."
            + "\nHere are some commands you can use:"
            + "\n  - todo\n  - deadline\n  - event"
            + "\n  - mark\n  - unmark\n  - list\n  - find\n  - delete\n  - archive\n  - bye");
    }
}
