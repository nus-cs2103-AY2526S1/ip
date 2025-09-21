package pengu.exception;

/**
 * Exception thrown when a field given is invalid.
 * e.g. invalid type / invalid format
 */
public class InvalidFieldException extends PenguException {
    /**
     * Constructor for the exception.
     * @param message Detailed error message.
     */
    public InvalidFieldException(String message) {
        super("Your field(s) provided caused the following error:\n"
                + message);
    }
}
