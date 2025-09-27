package steven.exception;

/**
 * Thrown when the user does not follow the correct syntax for creating a certain task.
 */
public class InvalidTaskFormatException extends StevenException {

    /**
     * Creates an instance of an InvalidTaskFormatException.
     *
     * @param taskType the type of task that the user attempted to create
     */
    public InvalidTaskFormatException(String taskType) {
        super("\tPls use the correct format for " + taskType + " task");
    }
}
