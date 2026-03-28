package tweety.exceptions;

/**
 * Exception thrown when the input format for a task description is invalid.
 * This exception is thrown when a user provides a task description in an incorrect format.
 * It includes a helpful error message to guide the user on how to provide a correctly formatted input.
 */
public class InvalidInputFormatException extends TweetyException{
    private static final String FORMATTING_GAP_DEFAULT = "     ";

    /**
     * Constructs an InvalidInputFormatException with a detailed message.
     *
     * @param message   A sample message or format suggestion for the user on how to provide a valid description format.
     * @param taskName  The name of the task that caused the exception, used to specify which task had the invalid input format.
     */
    public InvalidInputFormatException(String message, String taskName) {
        super(taskName + " description is of invalid format, hon.\n"
                + "Please follow this format ya: e.g. " + message);
    }
}
