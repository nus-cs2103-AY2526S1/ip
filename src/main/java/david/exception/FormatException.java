package david.exception;

/**
 * Throws an exception if the task format from the command is wrong.
 */
public class FormatException extends DavidException {
    public FormatException(String msg) {
        super(msg);
    }
}
