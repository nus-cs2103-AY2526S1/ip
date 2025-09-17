package errors;

/**
 * Custom runtime exception used by the Boop application.
 *
 * Represents errors related to command execution, task handling, or file
 * operations.
 */
public class BoopError extends RuntimeException {
    /**
     * Constructs a new BoopError with the specified detail message.
     *
     * @param message The error message describing the cause of the exception
     */
    public BoopError(String message) {
        super(message);
    }
}
