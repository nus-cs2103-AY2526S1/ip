package itachi;

/**
 * Represents exceptions that can occur in the Itachi bot application.
 *
 * <p>
 * This exception is thrown for any errors related to command parsing,
 * invalid user input, or other Itachi-specific operations.
 * </p>
 */
public class ItachiException extends Exception {
    public ItachiException(String message) {
        super(message);
    }
}
