package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a {@link Task} from the task list.
 * This command removes the task at the specified index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new {@code DeleteCommand} with the given index.
     *
     * @param index the index (0-based) of the task to be deleted
     */

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated list to storage, and returning a confirmation message.
     *
     * @param tasks   the task list from which to remove the task
     * @param ui      the user interface to display the confirmation message
     * @param storage the storage to save the updated task list
     * @return a message confirming the task was removed
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.remove(index);
        storage.save(tasks.getAll());
        return ui.showTaskRemoved(t, tasks.size());
    }
}
