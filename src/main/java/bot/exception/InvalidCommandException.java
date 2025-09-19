package bot.exception;

/**
 * Exception thrown when an invalid command is encountered in the bot application.
 * This exception is used to indicate that a command does not conform to the expected
 * format or is not recognized by the system.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor
     *
     * @param message Message regarding more info of Exception thrown
     */
    public InvalidCommandException(String message) {
        super("Invalid Command: " + message);
    }
}
