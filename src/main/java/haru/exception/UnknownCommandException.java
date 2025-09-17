package haru.exception;

/**
 * Exception thrown when a command is unknown.
 */
public class UnknownCommandException extends HaruException {

    /**
     * Constructs an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("Eh?! I don't recognize that command!");
    }
}
