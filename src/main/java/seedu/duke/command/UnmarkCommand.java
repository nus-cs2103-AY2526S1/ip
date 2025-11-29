package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private final int index; // 1-based

    /**
     * Creates a command to unmark a task.
     *
     * @param index 1-based index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark operation.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        final Task task = tasks.unmark(index);
        ui.show("Marked as not done:\n" + task);
        storage.save(tasks);
    }
}
