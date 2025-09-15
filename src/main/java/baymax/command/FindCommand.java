package baymax.command;

import baymax.task.TaskList;

/**
 * Represents a command that searches the task list for tasks
 * containing a specified keyword in their description.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the given task list for tasks
     * whose descriptions contain the specified keyword.
     *
     * @param tasks The {@link TaskList} to search through.
     * @return A formatted string containing the matching tasks, or a message
     *         indicating that no matches were found.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.find(this.keyword);
    }
}
