package briar.exception;

/**
 * Represents the exception thrown when a command has an invalid date.
 */
public class InvalidDateException extends BriarException {

    public InvalidDateException() {
        super("Aww, wrong date format! Use yyyy-mm-dd instead! >.<");
    }
}
