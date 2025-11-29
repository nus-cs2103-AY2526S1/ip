package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Deletes a task by index (1-based).
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a command to delete a task.
     *
     * @param index 1-based index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete operation.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Invalid index for delete.");
        }
        ui.show(tasks.deleteMessage(index));
        storage.save(tasks);
    }
}
