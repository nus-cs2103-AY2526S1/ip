package cate.exception;

/**
 * Exception thrown when a command that requires an index is either missing
 * the index argument or the provided index is invalid in the Cate application.
 * <p>
 * For example, {@code mark  } or {@code delete abc} would trigger this exception.
 * </p>
 */
public class InvalidIndexException extends CateException {

    /**
     * Constructs an {@code InvalidIndexException} with a default message
     * indicating that the index is required or invalid.
     */
    public InvalidIndexException() {
        super("Index required or invalid.");
    }
}
