package bobby.bobbyexception;

/**
 * Custom exception class for Bobby application errors.
 */
public class BobbyException extends Exception {
    private static final String DEFAULT_MESSAGE = "An error occurred in Bobby.";

    /**
     * Constructs a BobbyException with the specified error message.
     *
     * @param message The error message.
     */
    public BobbyException(String message) {
        super(message != null ? message : DEFAULT_MESSAGE);
    }

    /**
     * Constructs a BobbyException with the specified cause.
     *
     * @param cause The cause of the exception.
     */
    public BobbyException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    /**
     * Constructs a BobbyException with the specified error message and cause.
     *
     * @param message The error message.
     * @param cause The cause of the exception.
     */
    public BobbyException(String message, Throwable cause) {
        super(message != null ? message : DEFAULT_MESSAGE, cause);
    }
}