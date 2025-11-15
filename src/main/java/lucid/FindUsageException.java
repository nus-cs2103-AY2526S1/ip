package lucid;

/**
 * Exception resulting from using the find command incorrectly
 */
public class FindUsageException extends LucidException {
    public FindUsageException() {
        super("Something went wrong! Try using it like this: find <name>");
    }
}
