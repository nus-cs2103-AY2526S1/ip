package stewie.exceptions;

/**
 * Represents an exception thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends CommandException {

    /**
     * Creates a new UnknownCommandException with the list of available commands.
     *
     * @param message The list of available commands.
     */
    public UnknownCommandException(String message) {
        super(" The available commands are listed for your feeble mind to comprehend: \n"
                + message);
        assert message != null : "Exception message cannot be null";
    }
}
