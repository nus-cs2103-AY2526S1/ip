package dukeychatbot.dukeyexceptions;

/**
 * Constructs InvalidCommandException error which inherits from DukeyException.
 *
 * <p>Returns error when user inputs a command is not provided.</p>
 */
public class InvalidCommandException extends DukeyException {

    /**
     * Constructs the InvalidCommandException object.
     */
    public InvalidCommandException() {
        super("Pika! Invalid command, pika!\n"
                + "Valid commands:\n"
                + "- Add: 'todo'/'t', 'deadline'/'d', 'event'/'e'\n"
                + "- Mark: 'mark <num>' or 'm <num> \n"
                + "- Unmark: 'unmark <num>' or 'unm <num>'\n"
                + "- Delete: 'delete <num>' or 'del <num>'\n"
                + "- Find: 'find <keyword>' or 'f <keyword>'\n"
                + "Type them to see what happens, pika!");
    }
}
