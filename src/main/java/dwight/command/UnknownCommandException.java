package dwight.command;

/**
 * Exception thrown when the user inputs a command keyword that does not match any recognized
 * command.
 */
public class UnknownCommandException extends Exception {

    /**
     * Creates a new {@code UnknownCommandException} with a message indicating the unrecognized
     * command keyword.
     *
     * @param type The keyword of the unknown command.
     */
    public UnknownCommandException(String type) {
        super("Unknown command provided '" + type + "'");
    }
}
