package conversal.exception;

/**
 * Represents an unchecked exception specific to the Conversal chatbot.
 *
 * Used to signal errors that occur during command parsing,
 * task operations, or interactions with storage and UI.
 *
 */
public class ConversalException extends RuntimeException {

    /**
     * Constructs a new {@code ConversalException} with the specified message.
     *
     * @param message detail message describing the error
     */
    public ConversalException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Hmm, something seems wrong... " + getMessage();
    }
}
