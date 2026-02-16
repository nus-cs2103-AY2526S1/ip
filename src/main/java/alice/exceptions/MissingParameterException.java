package alice.exceptions;

/**
 * Thrown when a command is missing a required parameter.
 */
public class MissingParameterException extends AliceException {
    public MissingParameterException(String message) {
        super(message);
    }
}
