package airy;

/**
 * This is an exception for my chatbot
 * It is an unchecked exception and allows the code to throw it
 */
public class AiryException extends RuntimeException {

    /**
     * Constructs a new AiryException with the error message.
     *
     * @param message Error message that explains the reason for the exception.
     */
    public AiryException(String message) {
        super(message);
    }
}
