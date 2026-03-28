package cattis.command;

import cattis.CattisInterface;
import cattis.exception.CattisException;

/**
 * Represents an abstract command in the <code>Cattis</code> application.
 * <p>
 * Each command encapsulates a specific user instruction and defines
 * behavior to be executed within the context of the application.
 * Subclasses should implement the {@code void execute(CattisInterface cattis)} method
 * to define custom logic for each command.
 */
public abstract class Command {

    /**
     * Executes the command logic and modifies the internal state of the
     * <code>Cattis</code> application, such as updating the task list or
     * generating a response.
     *
     * @param cattis the application interface through which command operations are performed
     * @throws CattisException if an error occurs during command execution
     */
    public abstract void execute(CattisInterface cattis) throws CattisException;

    /**
     * Indicates whether this command should terminate the application.
     * <p>
     * Override this method in subclasses that represent exit commands (e.g. {@code ExitCommand}).
     *
     * @return {@code true} if the command signals application termination; {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}
