package jibjab;

/**
 * Custom checked exception used by the JibJab application to signal user and IO errors.
 */
public class JibJabException extends Exception {
    /**
     * Creates a new JibJabException with the specified detail message.
     *
     * @param message explanation of the problem
     */
    public JibJabException(String message) {
        super(message);
    }
}
