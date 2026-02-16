package ryuji.command;

import ryuji.storage.Storage;
import ryuji.task.TaskList;
import ryuji.ui.Ui;

/**
 * Represents a command to search for tasks that contain a given keyword.
 * <p>The {@code FindCommand} filters the list of tasks based on a search term
 * and displays the matching tasks to the user. It allows the user to quickly locate tasks
 * related to a specific keyword or phrase.</p>
 */
public class FindCommand extends Command {

    /**
     * The term to search for within the task descriptions.
     * This term is used to filter the task list for tasks that match the search term.
     */
    private final String searchTerm;

    /**
     * Constructs a {@code FindCommand} with the specified command keyword and search term.
     *
     * @param command    The command keyword (e.g., "find").
     * @param searchTerm The term to search for within the task descriptions.
     */
    public FindCommand(String command, String searchTerm) {
        super(command);
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the find command by filtering the tasks that contain the search term
     * and displaying the matching tasks through the UI.
     *
     * <p>This method delegates the task filtering to the {@code TaskList} and returns
     * a string representation of the matching tasks.</p>
     *
     * @param tasks   The current {@code TaskList} containing all tasks.
     * @param ui      The {@code Ui} instance used to display messages to the user.
     * @param storage The {@code Storage} instance used for saving/loading tasks (not used in this command).
     * @return a string representation of the tasks that match the search term.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.find(searchTerm).toString();
    }
}
