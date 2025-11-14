package shef.exception;

/**
 * Encapsulates most exceptions that can possibly happen during the runtime of the program.
 */
public class ShefException extends RuntimeException {
    public ShefException(String message) {
        super(message);
    }
}
