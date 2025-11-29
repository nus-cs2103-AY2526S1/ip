package cate.command;

import cate.task.Task;
import cate.task.TaskList;
import cate.ui.Ui;
import cate.util.Storage;

/**
 * Represents a command to find tasks in the task list that match a given query string.
 * This command does not modify the task list or storage.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param query the string to search for within task descriptions
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes the find command.
     * Searches the task list for tasks that match the query and returns a formatted message.
     *
     * @param storage the storage handler (not used)
     * @param tasks   the task list to search
     * @param ui      the UI handler (not used for this command)
     * @return a string listing all matching tasks, or a message indicating no matches
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task t : tasks.findTasks(query)) {
            output.append(t.toString()).append("\n");
        }
        return output.toString();
    }
}
