package commands;

import app.TaskList;
import errors.BoopError;

/**
 * Abstract base class for all user commands.
 *
 * Each concrete command class represents a specific action the user can perform
 * on the task list.
 * This class defines the common structure and behavior shared by all commands.
 */
public abstract class Command {
    /**
     * Executes the command, performing the necessary tasks
     * Uses parameters provided when command was generated
     *
     * @param tasklist Tasklist that may be used by the command
     * @throws BoopError Error related to Boop process
     */
    public abstract void execute(TaskList tasklist) throws BoopError;

    /**
     * @return UI message of the command
     */
    public abstract String getMessage();

    /**
     * @return Whether the command exits the process
     */
    public boolean isExit() {
        return false;
    }
}
