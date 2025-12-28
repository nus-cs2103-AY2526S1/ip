package benn.exceptions;

/**
 * Represents a custom checked exception used in Benn the Chatbot.
 *
 * <p>This exception is thrown when an error specific to the chatbot occurs,
 * such as invalid user input, parsing errors, or issues while modifying tasks.
 * It extends {@link Exception} so that it must be explicitly handled or declared.</p>
 */
public class BennException extends Exception {

    /**
     * Constructs a new {@code BennException} with the specified detail message.
     *
     * @param message the detail message describing the cause of the exception
     */
    public BennException(String message) {
        super(message);
    }

    /**
     * Returns the detail message string of this exception, with additional
     * context indicating that the error was raised in Benn the Chatbot.
     *
     * @return the formatted detail message string
     */
    @Override
    public String getMessage() {
        return String.format("%s Error raised with Benn the Chatbot; please try again!", super.getMessage());
    }
}
