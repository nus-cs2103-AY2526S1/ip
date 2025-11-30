package briar.exception;

/**
 * Represents the exception thrown when a command is formatted wrongly.
 */
public class WrongFormatException extends BriarException {

    public WrongFormatException() {
        super("Aww, the format is incorrect! >.<");
    }
}
