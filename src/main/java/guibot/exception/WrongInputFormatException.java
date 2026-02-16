package guibot.exception;

/**
 * Exception when user inputs a command in the wrong format.
 */
public class WrongInputFormatException extends GuibotException {
    /**
     * Creates a WrongInputFormatException with a message if the expected format is known.
     */
    public WrongInputFormatException(String expectedFormat) {
        super(String.format("Expected format: \"%s\".", expectedFormat));
    }

    /**
     * Creates a WrongInputFormatException with no message if the expected format is unknown.
     */
    public WrongInputFormatException() {
        super(null);
    }
}
