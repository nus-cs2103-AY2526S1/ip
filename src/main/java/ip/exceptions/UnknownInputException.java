package ip.exceptions;

/**
 * Exception for when the user input is not recognized
 */
public class UnknownInputException extends Exception {
    public UnknownInputException(String message) {
        super(message);
    }
}
