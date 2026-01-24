package chathyt.command;

/**
 *  Represents a command to delete a task.
 */

import java.util.ArrayList;
import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;
import chathyt.exception.InvalidCommandException;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     *
     * @param tasks The list of existing tasks.
     * @param storage The storage handler for saving tasks.
     * @param ui The UI handler for displaying messages.
     * @return A string message confirming the deleted task,
     *         or an error message if the input is invalid.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        try {
            if (index < 0 || index >= tasks.size()) {
                throw new InvalidCommandException("Invalid task number.");
            }

            Task taskToBeDeleted = tasks.get(index);
            tasks.remove(index);
            storage.save(tasks);
            return Ui.showRemovedTask(taskToBeDeleted, tasks.size());

        } catch (InvalidCommandException e) {
            return Ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false; // delete command does not exit the program
    }
}