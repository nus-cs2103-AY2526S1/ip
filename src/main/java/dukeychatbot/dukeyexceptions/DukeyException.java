package dukeychatbot.dukeyexceptions;

/**
 * Constructs DukeException error which are for errors specific for the chatbot.
 *
 * @author dongjun
 */
public class DukeyException extends Exception {

    /**
     * Constructs the DukeyException object.
     */
    public DukeyException(String message) {
        super(message);
    }

}
