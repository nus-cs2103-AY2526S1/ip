package alice.exceptions;

/**
 * Thrown when a parameter has an invalid type or unexpected value.
 */
public class InvalidParameterException extends AliceException {
    public InvalidParameterException(String message) {
        super(message);
    }
}
