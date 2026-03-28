package alex.command;

import alex.TaskList;

/**
 * Represents a command to display all tasks in the task list.
 * Returns a formatted list of tasks for the user.
 */
public class ListCommand implements Command {

    private TaskList taskList;

    /**
     * Constructs a <code>ListCommand</code> with the given task list.
     *
     * @param taskList Current task list to display.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns a formatted string of all tasks in the task list.
     *
     * @return List of tasks as a string.
     */
    @Override
    public String response() {
        return taskList.generateTaskList();
    }
}