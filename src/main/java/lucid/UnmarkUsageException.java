package lucid;

/**
 * Exception using from incorrect usage of the unmark exception
 */
public class UnmarkUsageException extends LucidException {
    public UnmarkUsageException() {
        super("That's not how you do it! Try: unmark <index> instead.");
    }
}
