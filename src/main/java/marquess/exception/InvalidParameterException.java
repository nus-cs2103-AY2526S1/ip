package marquess.exception;

/**
 * Thrown when an invalid parameter is passed to a command.
 */
public class InvalidParameterException extends MarquessException {
    public InvalidParameterException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("Invalid parameters were provided: %s", super.getMessage());
    }
}
