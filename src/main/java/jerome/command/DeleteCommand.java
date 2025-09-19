package jerome.command;

import jerome.Storage;
import jerome.task.Task;
import jerome.TaskList;
import jerome.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task from task list with the provided index.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save or load task data.
     * @return response message to indicate successful deletion of task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.remove(index);
        storage.save(tasks.getAll());
        return ui.showDeleted(t, tasks);
    }
}
