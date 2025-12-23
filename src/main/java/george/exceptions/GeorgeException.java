package george.exceptions;

/**
 * Represents a custom exception specific to the George application.
 * This exception is thrown when George encounters invalid input or operations.
 */
public class GeorgeException extends Exception {
    /**
     * Constructs a GeorgeException with the specified error message.
     *
     * @param message The detail message explaining the exception
     */
    public GeorgeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ARE YOU BANANAS?! " + getMessage();
    }
}
