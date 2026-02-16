package exception;

/**
 * Custom unchecked exception used in the Nicholas chatbot
 * <p>
 * This exception is thrown when a user input is unable to be processed
 * by the chatbot
 */
public class NicholasException extends Exception{
    public NicholasException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return  getMessage();
    }
}
