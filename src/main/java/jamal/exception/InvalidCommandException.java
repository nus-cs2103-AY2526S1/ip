package jamal.exception;

/**
 * Throws an Invalid Command Exception if Command is not recognised by Parser
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message) {
        super(message);
    }
}
