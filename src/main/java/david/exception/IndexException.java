package david.exception;

/**
 * Throws an exception if index from the command is out of bound.
 */
public class IndexException extends DavidException {
    public IndexException(String msg) {
        super(msg + " is out of bound.");
    }
}
