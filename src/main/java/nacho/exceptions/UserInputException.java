package nacho.exceptions;

/**
 * Custom Input Exception for errors surrounding wrong input by users
 */
public class UserInputException extends RuntimeException {
    // Custom exception that gets thrown when user is missing mandatory fields in their commands
    public UserInputException(String message) {
        super(message);
    }
}
