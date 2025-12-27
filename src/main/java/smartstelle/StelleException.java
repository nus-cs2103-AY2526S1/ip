package smartstelle;

/**
 * Custom exception class for the SmartStelle application.
 * <p>
 * Represents exceptions specific to task management operations,
 * such as invalid input or missing information when creating tasks.
 */
public class StelleException extends Exception {

    /**
     * Constructs a new StelleException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public StelleException(String message) {
        super(message);
    }
}