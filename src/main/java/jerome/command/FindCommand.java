package jerome.command;


import jerome.Storage;
import jerome.TaskList;
import jerome.Ui;

// Note: The Javadocs for this class were generated with AI assistance
/**
 * A {@code Command} that searches the task list for tasks containing
 * a specified keyword in their description.
 */
public class FindCommand extends Command{
    private String keyword;

    /**
     * Creates a {@code FindCommand} with the given search keyword.
     *
     * @param keyword The keyword to filter tasks by.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find operation by filtering the task list
     * for tasks that contain the keyword. The results are then
     * returned as a string for display to the user.
     * <p>
     * The current state of the task list is also saved to persistent storage.
     *
     * @param tasks   The {@code TaskList} containing all current tasks.
     * @param ui      The {@code Ui} object for user interaction.
     * @param storage The {@code Storage} handler used to save the current task list.
     * @return A string representation of the tasks that match the search keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getAll());
        return tasks.filter(this.keyword);
    }
}
