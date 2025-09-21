package jeff.storage;

/**
 * Custom exception class for Jeff chatbot system errors. Used to handle
 * application-specific error conditions.
 */
public class JeffException extends Exception {

    /**
     * Constructs a new JeffException with the specified error message.
     *
     * @param message the error message describing the exception
     */
    public JeffException(String message) {
        super(message);
    }
}
