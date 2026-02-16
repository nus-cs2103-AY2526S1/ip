package guibot.exception;

/**
 * Exception when data file is not in the expected format.
 */
public class DataFileCorruptedException extends GuibotException {
    /**
     * Creates a DataFileCorruptedException.
     */
    public DataFileCorruptedException() {
        super("Data file not formatted properly. Starting from empty task list.");
    }
}
