package jimbot.exception;

/**
 * Represents an EmptyList exception that occurs when
 * the user list is called on but is empty.
 */
public class EmptyListException extends JimbotException {
    /**
     * Constructs an EmptyList exception with the specified error message.
     */
    public EmptyListException(String command) {
        super("( ㄏ-᷅_-᷄)ㄏ You've nothing to " + command + "...");
    }
}
