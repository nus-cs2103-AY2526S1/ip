package pepe.exception;

/**
 * Custom exception class for the Pepe application.
 * <p>
 * This exception is thrown when a user input or operation
 * does not follow the expected format or rules of the application.
 * It provides a friendly, customized error message to the user.
 */
public class PepeExceptions extends Exception {
    public PepeExceptions(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return ("Hey bro...I am going to tell you nicely!\n" + super.getMessage());
    }
}
