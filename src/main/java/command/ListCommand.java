package command;

import tasklist.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 * Displays all current tasks to the user when executed.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with LIST command type.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * {@inheritDoc}
     * Displays all tasks currently in the task list.
     *
     * @param taskList the task list containing tasks to display
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.printTasks();
    }
}
