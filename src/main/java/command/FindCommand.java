package command;

import java.util.ArrayList;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 */
public class FindCommand extends Command {
    private final String KEYWORD;

    /**
     * Creates a new {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.KEYWORD = keyword;
    }

    /**
     * Executes the find command by searching for tasks whose descriptions
     * contain the specified keyword, and returns a formatted list of matches.
     *
     * @param tasks   The task list to search.
     * @param ui      The UI component used to display the results.
     * @param storage The storage component (not used in this command).
     * @return A message displaying the tasks that match the keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = tasks.findTasks(KEYWORD);
        return ui.showFoundTasks(matches);
    }
}
