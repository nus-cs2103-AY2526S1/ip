package v.command;

import java.util.List;

import v.storage.Storage;
import v.task.Task;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to find tasks containing a keyword (case-insensitive).
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} that searches for tasks with descriptions
     * containing the given keyword (case-insensitive).
     *
     * @param keyword The search term to match within task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command by filtering tasks in {@code TaskList} that contain the keyword
     * and displaying them via {@code Ui}.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI used to display results.
     * @param storage The storage (unused).
     * @return False to continue running the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(keyword);
        ui.showFindResults(matches);
        return false;
    }
}
