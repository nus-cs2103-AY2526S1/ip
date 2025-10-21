package geegar.exception;

/**
 * Thrown when the user input does not have any keyword
 */
public class UnknownCommandException extends GeegarException {

    public UnknownCommandException(String command) {
        super("I don't know what " + command + " means, try 'todo', 'deadline' or 'event' to add a task!");
    }
}