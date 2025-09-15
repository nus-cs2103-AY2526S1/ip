package baymax.command;

import baymax.exception.BaymaxException;
import baymax.task.TaskList;

/**
 * Represents a user command that can be executed in the baymax.Baymax application.
 */
public abstract class Command {
    /**
     * Executes the command using the provided task list and user interface.
     *
     * @param tasks The {@link TaskList} containing the user's tasks.
     * @return A formatted message describing the result of executing the command.
     * @throws BaymaxException If the command cannot be executed successfully.
     */
    public abstract String execute(TaskList tasks) throws BaymaxException;

    /**
     * Determines whether this command should terminate the program.
     *
     * @return {@code true} if this command terminates the program, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
