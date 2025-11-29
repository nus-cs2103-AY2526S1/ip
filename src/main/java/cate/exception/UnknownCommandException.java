package cate.exception;

/**
 * Exception thrown when the user inputs a command
 * that is not recognized by the Cate application.
 */
public class UnknownCommandException extends CateException {

    /**
     * Constructs an {@code UnknownCommandException} with a message
     * indicating the unrecognized command.
     *
     * @param command The unrecognized command input by the user.
     */
    public UnknownCommandException(String command) {
        super("I'm sorry I don't understand: " + command);
    }
}
