package beebong.command;

import beebong.task.TaskList;

/**
 * Represents a Command for listing tasks from the task list that contain
 * a specific keyword in its name.
 */
public class FindTaskCommand extends ListTasksCommand {
    private final String keyword;

    /**
     * Creates a new {@code FindTaskCommand} with the given search keyword.
     *
     * @param keyword the keyword to search for within task names.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a filtered {@link TaskList} that contains only tasks with the keyword in its task name.
     *
     * @param taskList the original list of tasks.
     * @return a new {@code TaskList} containing the tasks to be displayed.
     */
    @Override
    protected TaskList createTaskList(TaskList taskList) {
        return taskList.findTasks(this.keyword);
    }
}
