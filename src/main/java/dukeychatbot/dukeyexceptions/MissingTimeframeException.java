package dukeychatbot.dukeyexceptions;

/**
 * Constructs MissingTimeframeException error which inherits from DukeyException.
 *
 * <p>Returns error when user fails to provide timeframe for Event tasks.</p>
 */
public class MissingTimeframeException extends DukeyException {

    /**
     * Constructs the MissingTimeframeException object.
     */
    public MissingTimeframeException() {
        super("WARNING: Event command requires a timeframe.\n"
              + "Valid input requires '/from' AND '/to' keyword. "
              + "Follow the format: event <task name> /from <date / timing> /to <date / timing> \n"
              + "Dates can be written in yyyy-mm-dd format or just plain text\n"
              + "E.g. event project meeting /from Mon 2pm /to 4pm\n"
              + "E.g. music festival /from 2025-05-07 /to 2025-05-20");
    }
}
