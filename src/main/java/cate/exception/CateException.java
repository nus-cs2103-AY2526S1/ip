package cate.exception;

/**
 * The base class for all exceptions thrown by the Cate application.
 * <p>
 * A {@code CateException} indicates that an error has occurred
 * during command processing or that the user provided invalid input.
 * Specific exception types should extend this class to represent
 * particular error scenarios.
 * </p>
 */
public class CateException extends Exception {

    /**
     * Constructs a {@code CateException} with the specified detail message.
     *
     * @param message A descriptive message explaining the reason for the exception.
     */
    public CateException(String message) {
        super(message);
    }
}
