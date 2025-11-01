package nina.command;

import nina.CommandException;
import nina.task.TaskList;

/**
 * Represents a command that can be executed on a TaskList.
 */
public interface Command {

    /**
     * Executes the command on the given TaskList.
     *
     * @param tasks the task list the command operates on
     * @throws CommandException if the command cannot be executed successfully
     */
    String execute(TaskList tasks) throws CommandException;
}
