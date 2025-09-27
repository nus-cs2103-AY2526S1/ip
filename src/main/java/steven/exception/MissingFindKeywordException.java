package steven.exception;

/**
 * Thrown when a user attempts to execute a find command without a keyword.
 */
public class MissingFindKeywordException extends StevenException {
    public MissingFindKeywordException() {
        super("\tFind WHAT???");
    }
}
