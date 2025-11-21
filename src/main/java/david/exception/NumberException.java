package david.exception;

/**
 * Throws an exception if the index from the command
 * cannot be parsed into an integer.
 */
public class NumberException extends DavidException {
    public NumberException(String msg) {
        super(msg + " is not a valid integer.");
    }
}
