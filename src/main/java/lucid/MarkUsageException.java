package lucid;

/**
 * Exception resulting from incorrect usage of the mark command
 */
public class MarkUsageException extends LucidException {
    public MarkUsageException() {
        super("That's not how you do it! Try: mark <index> instead.");
    }
}
