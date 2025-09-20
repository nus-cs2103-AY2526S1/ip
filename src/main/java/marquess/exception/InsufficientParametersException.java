package marquess.exception;

/**
 * Thrown when insufficient parameters are provided for a command.
 */
public class InsufficientParametersException extends MarquessException {
    public InsufficientParametersException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("The command has insufficient parameters: %s", super.getMessage());
    }
}
