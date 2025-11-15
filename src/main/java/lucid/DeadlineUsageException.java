package lucid;

/**
 * Exception resulting from incorrect usage of the deadline command
 */
public class DeadlineUsageException extends LucidException {
    /**
     * Constructor for exception, with message informing user of the correct usage method
     */
    public DeadlineUsageException() {
        super("Looks like you've used the deadline command incorrectly!\n"
                + "Proper usage: deadline <name> /by yyyy-mm-dd (or yyyy-mm-dd-xxxx to include a timing)");
    }
}
