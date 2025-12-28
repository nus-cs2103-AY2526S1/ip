package bytebuddy.command;

import bytebuddy.exception.MissingDescriptionException;
import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

/**
 * Represents a command that searches for tasks containing a given keyword.
 * <p>
 * The {@code FindCommand} filters the existing {@link TaskList} for tasks
 * that match the specified keyword. If no keyword is provided, a
 * {@link MissingDescriptionException} is thrown.
 * </p>
 */
public class FindCommand extends Command {

    /** The keyword to search for within the task descriptions. */
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given search keyword.
     *
     * @param keyword the keyword to search for in tasks
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the find operation.
     * <p>
     * If the keyword is empty, a {@link MissingDescriptionException} is thrown.
     * Otherwise, it returns a formatted list of all matching tasks.
     * </p>
     *
     * @param storage the storage manager (not modified by this command)
     * @param tasks   the current list of tasks
     * @param ui      the user interface for formatting feedback messages
     * @return a feedback message listing tasks that match the keyword
     * @throws MissingDescriptionException if the keyword is empty
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (keyword.isEmpty()) {
            throw new MissingDescriptionException("find");
        }
        TaskList results = tasks.findTasks(keyword);
        return ui.getTaskListMessage(results);
    }
}
