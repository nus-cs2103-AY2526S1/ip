package ember.exception;

/**
 * Exception thrown when a user provides incomplete or missing information for a command.
 * This custom exception helps alert the user to fill in required fields.
 */
public class MissingInformationException extends Exception {
    /**
     * @param message the detail message explaining what information is missing
     */
    public MissingInformationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "You have entered an invalid input.\n" +
                super.getMessage();
    }
}