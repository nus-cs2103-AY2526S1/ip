package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Marks a task as done.
 */
public class MarkCommand extends Command {

    private final int index; // 1-based

    /**
     * Creates a command to mark a task as done.
     *
     * @param index 1-based index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark operation.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException If the index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        final Task task = tasks.mark(index);
        ui.show("Marked as done:\n" + task);
        storage.save(tasks);
    }
}
