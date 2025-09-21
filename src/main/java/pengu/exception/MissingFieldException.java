package pengu.exception;

/**
 * Exception thrown when a command given has missing field(s).
 */
public class MissingFieldException extends PenguException {
    /**
     * Constructor for the exception.
     * @param message Detailed error message.
     */
    public MissingFieldException(String message) {
        super("Your command is missing a field!\n"
                + "Please output the command in the following format:\n"
                + message);
    }
}
