package Coffee;

import java.util.List;

/**
 * Represents the {@code find} command.
 * Searches for tasks whose descriptions contain the specified keyword
 * and displays the matching tasks to the user.
 */
public class FindCommand extends Command {

    /** Search keyword used to filter tasks. */
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given keyword.
     * Leading and trailing whitespace is trimmed before use.
     *
     * @param keyword keyword to search for within task descriptions.
     * @throws NullPointerException if {@code keyword} is {@code null}.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes this command by finding tasks that contain the keyword and
     * printing the matches via the {@link Ui}.
     *
     * @param tasks   task list to search.
     * @param ui      UI used to display results.
     * @param storage storage (not used by this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(keyword);
        ui.showMessage("Here are the matching tasks in your list: ");
        for (Task t: matches) {
            ui.showMessage(t.toString());
        }
    }

    /**
     * Returns {@code false} to indicate the application should continue running
     * after this command completes.
     *
     * @return {@code false}, as this command does not request application exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
