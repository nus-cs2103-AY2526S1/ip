package jackbot;

/**
 * Checked exception for Jackbot domain errors (e.g., invalid commands,
 * storage failures, or illegal task operations).
 *
 * <p>Prefer throwing this from parsing, validation, and storage layers so the
 * UI can display friendly messages without exposing internal details.</p>
 *
 */
public class JackbotException extends Exception {

    /**
     * Creates a new {@code JackbotException} with a detail message.
     *
     * @param message human-readable explanation of the error
     */
    public JackbotException(String message) { super(message); }
}
