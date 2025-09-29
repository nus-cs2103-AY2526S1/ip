package floydai;

/**
 * Custom exception class for FloydAI chatbot application.
 * Used to indicate errors in user commands, input parsing, or other
 * application-specific failures.
 */
public class FloydException extends Exception {

    /**
     * Constructs a new FloydAIException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public FloydException(String message) {
        super(message);
    }
}
