package tweety.exceptions;

/**
 * Exception thrown when an invalid task number is provided.
 * This exception is thrown when a user provides a non-existent task number or one that is out of the valid range.
 * The error message provides a simple prompt for the user to enter a valid task number.
 */
public class InvalidTaskNumberException extends TweetyException {

    /**
     * Constructs an InvalidTaskNumberException with a default error message.
     * The message prompts the user to provide a valid task number.
     */
    public InvalidTaskNumberException() {
        super("Aww, hon, please give me a valid task number!");
    }
}
