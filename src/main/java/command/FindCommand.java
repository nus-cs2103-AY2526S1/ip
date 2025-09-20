package command;

import taskmodule.Task;

/**
 * Represents a command that searches for tasks in the task list
 * that contain a given keyword in their descriptions.
 * <p>
 * The {@code FindCommand} allows users to quickly locate tasks
 * matching a specified keyword by filtering through all existing tasks.
 * </p>
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Searches for tasks that contain the keyword in their descriptions.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " book";

    public final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     * @throws AssertionError if {@code keyword} is {@code null}
     */
    public FindCommand(String keyword) {
        assert keyword != null : "Keyword should not be null";

        this.keyword = keyword;
    }

    /**
     * Returns a list of tasks whose descriptions contain the keyword.
     * If there are no tasks, informs the user that the list is empty.
     *
     * @return a formatted string containing the matching tasks, or a message
     *         indicating that there are no tasks
     */
    public String listMatchingTasks() {
        int taskCount = taskList.getTaskCount();

        if (taskCount == 0) {
            return "You have no tasks.";
        }

        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(this.keyword)) {
                matchingTasks.append(i + 1).append(". ").append(task).append("\n");
            }
        }

        return matchingTasks.toString();
    }

    /**
     * Executes the find command and returns the result message
     * containing matching tasks.
     *
     * @return the formatted list of matching tasks, or a message if no matches are found
     */
    @Override
    public String respond() {
        return this.listMatchingTasks();
    }
}
