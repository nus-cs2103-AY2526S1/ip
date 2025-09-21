package pengu.exception;

/**
 * Base exception class for the project.
 */
public class PenguException extends Exception {
    /**
     * Constructor for the exception.
     * @param message Detailed error message.
     */
    public PenguException(String message) {
        super("Oh no! " + message);
    }
}
