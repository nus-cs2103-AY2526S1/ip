package nina;

/**
 * Handles exceptions that may occur in executing a task
 */
public class CommandException extends Exception {
    /**
     * Constructs a CommandException
     * @param msg error message of the exception
     */
    public CommandException(String msg) {
        super(msg);
    }
}
