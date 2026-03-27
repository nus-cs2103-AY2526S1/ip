package exceptions;

/**
 * Base type for all domain-specific exceptions in Sunday.
 */
public class SundayException extends Exception {
    public SundayException(String message) {
        super(message);
    }
}
