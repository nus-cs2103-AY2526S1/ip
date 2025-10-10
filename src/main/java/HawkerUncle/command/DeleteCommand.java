package HawkerUncle.command;

import HawkerUncle.storage.Storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.ui.Ui;
import HawkerUncle.task.Task;
import java.io.IOException;

/**
 * Represents the "Delete" command to remove a task from the task list
 */
public class DeleteCommand implements Command {
    private final int idx;

    /**
     * Initialises the DeleteCommand
     * @param idx The index of the task to be removed from the task list.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to delete a task from the task list.
     * @param tasks The task list where tasks are stored
     * @param ui The user interface where messages are shown to the user.
     * @param storage The storage object where tasks are saved and laoded.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.size()) {
            return Ui.showError("Task number is invalid.");
        }
        Task removed = tasks.remove(idx);

        try {
            storage.save(tasks);
        } catch(IOException e) {
            return Ui.showError("Unable to save tasks");
        }
        return Ui.showTaskDeleted(removed, tasks.size());
    }

    /**
     * Checks if the command is an exit command.
     * @return false, since the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
