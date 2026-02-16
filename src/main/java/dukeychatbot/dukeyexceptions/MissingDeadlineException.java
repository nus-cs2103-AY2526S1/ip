package dukeychatbot.dukeyexceptions;

/**
 * Constructs MissingDeadlineException error which inherits from DukeyException.
 *
 * <p>Returns error when user fails to provide deadline for Deadline tasks.</p>
 */
public class MissingDeadlineException extends DukeyException {

    /**
     * Constructs the MissingDeadlineException object.
     */
    public MissingDeadlineException() {
        super("WARNING: Deadline command requires a deadline.\n"
              + "Valid input requires '/by' keyword. Follow the format: deadline <task name> /by <date / timing>\n"
              + "Dates can be written in yyyy-mm-dd format or just plain text\n"
              + "E.g. deadline Wash Clothes /by 2025-08-12");
    }
}
