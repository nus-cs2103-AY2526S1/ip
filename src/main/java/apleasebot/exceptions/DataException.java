package apleasebot.exceptions;

/**
 * Exception thrown when there are issues related to reading and writing
 * the storage file
 */
public class DataException extends APleaseBotException {
    public DataException(String message) {
        super("Issue with data file: " + message);
    }
}
