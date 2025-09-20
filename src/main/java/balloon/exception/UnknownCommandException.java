package balloon.exception;

/**
 * Represents an exception that is thrown when the user inputs
 * a command that is not recognised by the Balloon program.
 */
public class UnknownCommandException extends BalloonException {
    public UnknownCommandException() {
        super("Unrecognised command received");
    }
}
