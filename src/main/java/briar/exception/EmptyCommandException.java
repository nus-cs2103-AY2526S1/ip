package briar.exception;

/**
 * Represents the exception thrown when a command has an empty description.
 */
public class EmptyCommandException extends BriarException {

    public EmptyCommandException(String command) {
        super("Aww, the description of " + command + " cannot be empty! >.<");
    }
}
