package lucid;

/**
 * Exception resulting from incorrect usage of the event command
 */
public class EventUsageException extends LucidException {
    /**
     * Constructor for exception, with message informing user of the correct usage method
     */
    public EventUsageException() {
        super("Looks like you've used the event command incorrectly!\n"
                + "Proper usage: event <name> /from yyyy-mm-dd /to yyyy-mm-dd "
                + "(or yyyy-mm-dd-xxxx to include a timing)");
    }
}
