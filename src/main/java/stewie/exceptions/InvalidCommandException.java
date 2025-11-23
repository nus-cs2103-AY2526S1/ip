package stewie.exceptions;

/**
 * Represents an exception thrown when a command has invalid format or parameters.
 */
public class InvalidCommandException extends CommandException {

    /**
     * Creates a new InvalidCommandException with the specified message.
     *
     * @param message The correct format message to display.
     */
    public InvalidCommandException(String message) {
        super("I've provided the correct format for your simpleton mind.\n"
                + "Follow it precisely: " + message);
        assert message != null : "Exception message cannot be null";
    }
}
