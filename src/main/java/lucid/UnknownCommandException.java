package lucid;

/**
 * Exception when detecting a command that is not recognised.
 */
public class UnknownCommandException extends LucidException {
    public UnknownCommandException() {
        super("Sorry, I can't understand you...");
    }
}
