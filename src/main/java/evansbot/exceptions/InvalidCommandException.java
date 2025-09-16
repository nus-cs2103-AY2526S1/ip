package evansbot.exceptions;

/**
 * Represents an InvalidCommand exception that occurs when an invalid command is given.
 */
public class InvalidCommandException extends EvansBotException {
    /**
     * Constructs an InvalidCommand Exception with the specified error message.
     */
    public InvalidCommandException() {
        super("""
                Error! Sorry! I don't know what this comment is supposed to be...\s
                Available commands: todo (description) , event (description) (from) (to), deadline (description) (by)
                Type 'bye' to cancel the chat!""");
    }

    /**
     * Constructs an InvalidCommand Exception with the specified error message.
     *
     * @param message Description of the exception.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
