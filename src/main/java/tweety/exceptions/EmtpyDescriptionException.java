package tweety.exceptions;

/**
 * Exception thrown when a task description is found to be empty.
 * This exception is thrown when a user attempts to create a task without providing a description.
 * It provides a formatted error message, instructing the user to follow the correct format.
 */
public class EmtpyDescriptionException extends TweetyException {
    private static final String FORMATTING_GAP_DEFAULT = "     ";

    /**
     * Constructs an EmptyDescriptionException with a detailed message.
     *
     * @param message   A sample message or format suggestion for the user on how to provide a valid description.
     * @param taskName  The name of the task that caused the exception, used to specify which task had an empty description.
     */
    public EmtpyDescriptionException(String message, String taskName) {
        super(taskName + " description cannot be empty, hon.\n"
                + "Please follow this format ya: e.g. " + message);
    }
}
