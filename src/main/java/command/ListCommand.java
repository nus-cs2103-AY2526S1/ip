package command;

import taskmodule.Task;


/**
 * Represents the {@code list} command which displays all tasks
 * currently in the task list.
 *
 * <p>When executed, this command retrieves all tasks from the global
 * {@link Command#taskList} and returns a formatted string showing
 * their details in order.</p>
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks in the task list."
            + "Example: " + COMMAND_WORD;

    /**
     * Builds a string representation of all tasks in the task list.
     *
     * @return the formatted task list, or a message indicating no tasks exist
     */
    public String listTasks() {
        int taskCount = taskList.getTaskCount();

        if (taskCount == 0) {
            return "You have no tasks.";
        }

        StringBuilder taskListString = new StringBuilder("Your tasks:\n");
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.getTask(i);
            taskListString.append(i + 1).append(". ").append(task).append("\n");
        }

        return taskListString.toString();
    }

    /**
     * Executes this command by returning the list of tasks.
     *
     * @return the formatted task list, or a message indicating no tasks exist
     */
    @Override
    public String respond() {
        return this.listTasks();
    }
}
