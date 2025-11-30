package briar.exception;

/**
 * Represents the exception thrown when a command is invalid.
 */
public class InvalidCommandException extends BriarException {

    public InvalidCommandException() {
        super("Aww, I don't know what that is! >.<");
    }
}
