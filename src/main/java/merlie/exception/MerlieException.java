package merlie.exception;

/**
 * Represents exceptions thrown by the Merlie application.
 * Used to indicate errors in parsing, file operations, or task handling.
 */
public class MerlieException extends Exception {
    /**
     * Constructs a MerlieException with the specified error message.
     *
     * @param message Description of the exception.
     */
    public MerlieException(String message) {
        super(message);
    }
}
