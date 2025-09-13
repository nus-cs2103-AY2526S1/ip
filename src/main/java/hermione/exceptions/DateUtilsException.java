package hermione.exceptions;

/**
 * Represents an exception that occurs in the DateUtils class.
 */
public class DateUtilsException extends RuntimeException {
    public DateUtilsException(String message) {
        super("[ERROR] Date Error: " + message);
    }
}
