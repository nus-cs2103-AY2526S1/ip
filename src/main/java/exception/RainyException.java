package exception;

/**
 * Represents a custom exception used in the Rainy task management application.
 * This exception is thrown when an error occurs during task operations,
 * such as invalid input, invalid task index, or storage issues.
 */
public class RainyException extends Exception {
    public RainyException(String message) {
        super(message);
    }
}
