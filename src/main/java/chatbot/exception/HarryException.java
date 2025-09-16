package chatbot.exception;

/**
 * Represents an exception specific to the Harry chatbot application.
 * This exception is thrown when an error related to chatbot operations occurs.
 */
public class HarryException extends RuntimeException {

    /**
     * Constructs a new {@code HarryException} with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception
     */
    public HarryException(String message) {
        super(message);
    }
}
