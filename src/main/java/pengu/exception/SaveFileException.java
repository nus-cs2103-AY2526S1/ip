package pengu.exception;

/**
 * Exception thrown when there are errors in the save file.
 */
public class SaveFileException extends PenguException {
    /**
     * Constructor for the exception.
     * @param message Detailed error message.
     */
    public SaveFileException(String message) {
        super("An error occurred with the save file!\n" + message);
    }
}
