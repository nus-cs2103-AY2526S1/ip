package baymax.command;

import baymax.task.TaskList;

/**
 * Represents a command that lists all tasks currently in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by retrieving all tasks from the given task list
     * and returning them in a formatted string.
     *
     * @param tasks The {@link TaskList} containing all current tasks.
     * @return A formatted string representation of the entire task list.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
