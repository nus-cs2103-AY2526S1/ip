package peppa.command;

/**
 * Represents a user command that can be executed to produce a string response.
 */
public interface Command {
    /**
     * Execute the command and return a response string.
     */
    String execute();
}
