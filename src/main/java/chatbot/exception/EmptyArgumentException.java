package chatbot.exception;

/**
 * Exception thrown when an argument that is expected to be non-empty
 * is found to be empty. This helps in explicitly handling cases
 * where user input or required parameters are missing.
 */
public class EmptyArgumentException extends Exception {

    /**
     * Constructs a new {@code EmptyArgumentException} with the specified detail message.
     *
     * @param message the detail message that explains the cause of this exception
     */
    public EmptyArgumentException(String message) {
        super(message);
    }
}

// Javadoc comments above were generated with assistance from ChatGPT.
