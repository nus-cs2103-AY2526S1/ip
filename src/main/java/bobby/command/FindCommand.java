package bobby.command;

import java.util.ArrayList;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * Command to find and display tasks matching a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, displaying tasks that match the keyword.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> found = tasks.find(keyword);
        if (found.isEmpty()) {
            return "No matching tasks found, fam!";
        }
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < found.size(); i++) {
            response.append(System.lineSeparator())
                    .append((i + 1)).append(". ")
                    .append(found.get(i).toString());
        }
        return response.toString();
    }
}
