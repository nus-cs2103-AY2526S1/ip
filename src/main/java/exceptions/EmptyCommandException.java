package exceptions;

/**
 * Thrown when the user submits an empty command line.
 */
public class EmptyCommandException extends SundayException {
    public EmptyCommandException() {
        super("Can you please type something? (OwO)");
    }
}
