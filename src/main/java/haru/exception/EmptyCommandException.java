package haru.exception;

/**
 * Exception thrown when a command is empty.
 */
public class EmptyCommandException extends HaruException {

    /**
     * Constructs an EmptyCommandException.
     */
    public EmptyCommandException() {
        super("Eh?! You should give me a command!");
    }
}
