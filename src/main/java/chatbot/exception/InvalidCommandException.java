package chatbot.exception;

/**
 * Exception thrown when a user enters an invalid command.
 * This ensures that invalid or unrecognized commands are explicitly
 * flagged and handled, improving program robustness and user feedback.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs a new {@code InvalidCommandException} with a custom message
     * that includes the invalid command entered by the user.
     *
     * @param command the invalid command string that caused this exception
     */
    public InvalidCommandException(String command) {
        super("Invalid command: \"" + command + "\". Please enter a valid command. \nBEEP B00P");
    }
}

// Javadoc comments above were generated with assistance from ChatGPT.
