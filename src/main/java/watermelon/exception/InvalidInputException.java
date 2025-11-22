package watermelon.exception;

/**
 * An exception that is thrown when an input field is invalid.
 */
public class InvalidInputException extends WatermelonException {
    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message The detail message explaining invalid input.
     *                The detail message is saved for later retrieval by the
     *                {@link Throwable#getMessage()} method.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
