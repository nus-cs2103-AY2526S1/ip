package nina;

/**
 * Handles exceptions that may occur when input is invalid
 */
public class InvalidInputException extends RuntimeException{
    /**
     * Constructs an InvalidInputException
     * @param msg error message of the exception
     */
    public InvalidInputException(String msg) {
        super(msg);
    }
}
