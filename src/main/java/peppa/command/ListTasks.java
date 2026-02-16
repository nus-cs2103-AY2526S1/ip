package peppa.command;

import peppa.task.TaskList;

/**
 * Lists all tasks in the given TaskList.
 */
public class ListTasks implements Command {
    private final TaskList tasks;

    /**
     * Creates a new ListTasks command.
     *
     * @param tasks the task list to read from (must not be null).
     */
    public ListTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the tasks to be displayed to the user.
     *
     * @return formatted task list.
     */
    @Override
    public String execute() {
        return tasks.displayTasks();
    }
}
