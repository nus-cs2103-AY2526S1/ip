package bugsbunny.exception;

/**
 * Represents a user-facing error thrown during command parsing or execution.
 */
public class BugsBunnyException extends Exception {
    /**
     * @param message Explanation suitable for printing to the console.
     */
    public BugsBunnyException(String message) {
        super(message);
    }
}
