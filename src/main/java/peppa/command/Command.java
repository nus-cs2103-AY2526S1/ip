package peppa.command;

/**
 * Represents a user command that can be executed to produce a string response.
 * Implementations should perform actions (possibly mutating collaborators) and
 * return a user-facing message describing the result.
 */
public interface Command {
    /**
     * Returns the user-facing response produced by executing this command.
     *
     * @return a user-facing response message.
     */
    String execute();
}
