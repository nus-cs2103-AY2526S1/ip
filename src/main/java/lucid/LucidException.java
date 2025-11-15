package lucid;

/**
 * General exception for all exceptions created for the chatbot. Superclass of all other created exceptions
 */
public class LucidException extends Exception {
    public LucidException(String message) {
        super(message);
    }
}
