package lyra.exception;

/**
 * Custom exception class for Lyra application-specific errors.
 * Extends the standard Exception class to provide application-specific error handling.
 */
public class LyraException extends Exception {
    /**
     * Constructs a new LyraException with the specified error message.
     *
     * @param message the error message describing what went wrong
     */
    public LyraException(String message) {
        super(message);
    }
}



