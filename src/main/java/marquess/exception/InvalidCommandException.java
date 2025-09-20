package marquess.exception;

/**
 * Thrown when an invalid command is attempted.
 */
public class InvalidCommandException extends MarquessException {
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("The command does not exist: %s", super.getMessage());
    }
}
