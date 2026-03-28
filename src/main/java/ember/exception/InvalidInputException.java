package ember.exception;
/**
 * Exception thrown when user input is invalid or does not match expected commands.
 * This is a custom exception used to signal input errors to the user.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs a new {@code InvalidInputException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}