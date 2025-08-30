package phuc.exception;

/**
 * Custom exception class for Phuc application-specific errors.
 * Extends the standard Exception class to provide application-specific error handling.
 */
public class PhucException extends Exception {
    /**
     * Constructs a new PhucException with the specified error message.
     *
     * @param errorMessage the detail message describing the error
     */
    public PhucException(String errorMessage) {
        super(errorMessage);
    }
}
