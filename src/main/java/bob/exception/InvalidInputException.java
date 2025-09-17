package bob.exception;

/**
 * Exception for invalid user input.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs an InvalidInputException.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the getMessage() method.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
