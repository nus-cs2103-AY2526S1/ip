package balloon.exception;

/**
 * Represents an exception thrown when the user input is blank.
 */
public class NoCommandException extends BalloonException {
    public NoCommandException() {
        super("You did not provide any command.");
    }
}
