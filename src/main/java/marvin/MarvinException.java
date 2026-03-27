package marvin;

/**
 * The exception class used by Parsers to throw errors related to Marvin.
 */
public class MarvinException extends RuntimeException {

    /**
     * Instantiates a Marvin Exception with a given descriptive text
     */
    public MarvinException(String deleteFormatText) {
        super(deleteFormatText);
    }
}
