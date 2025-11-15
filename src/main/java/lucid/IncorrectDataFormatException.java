package lucid;

/**
 * Exception resulting from detection of invalid data retrieved from data file
 */
public class IncorrectDataFormatException extends LucidException {
    public IncorrectDataFormatException() {
        super("Oh no! Something went wrong with reading the data file!");
    }
}
