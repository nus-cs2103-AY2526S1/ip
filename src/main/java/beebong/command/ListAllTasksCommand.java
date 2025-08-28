package beebong.command;

import beebong.task.TaskList;

/**
 * Represents a Command for listing all tasks from the task list.
 */
public class ListAllTasksCommand extends ListTasksCommand {
    /**
     * Returns the given {@link TaskList}.
     *
     * @param taskList the original list of tasks.
     * @return a new {@code TaskList} containing the tasks to be displayed.
     */
    @Override
    protected TaskList createTaskList(TaskList taskList) {
        return taskList;
    }
}
