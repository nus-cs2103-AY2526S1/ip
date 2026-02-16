package july.error;

/**
 * Custom runtime exception for the July task management application.
 * This exception is used throughout the application to handle various error
 * conditions such as invalid user input, command parsing errors, and task
 * operation failures that should be displayed to the user.
 */
public class JulyException extends RuntimeException {

    /**
     * Constructs a JulyException with the specified error message.
     *
     * @param message the detail message explaining the error condition
     */
    public JulyException(String message) {
        super(message);
    }
}
