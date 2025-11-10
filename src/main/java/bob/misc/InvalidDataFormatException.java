package bob.misc;

/**
 * Exception to be thrown if there is an invalid date format
 */
public class InvalidDataFormatException extends Exception{
    public InvalidDataFormatException() {
        super();
    }

    public InvalidDataFormatException(String message) {
        super(message);
    }
}
