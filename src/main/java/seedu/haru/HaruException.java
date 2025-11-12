package seedu.haru;

/**
 * Represents exceptions specific to the Haru application.
 * Thrown when an invalid operation or input is encountered.
 */
public class HaruException extends Exception {
    /**
     * Constructs a new HaruException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public HaruException(String message) {
        super(message);
    }
}
