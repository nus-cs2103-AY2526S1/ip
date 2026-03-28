package clippy.command;

import clippy.ClippyException;
import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a command to unmark a task as completed.
 */
public class UnmarkCommand extends Command {
    private final int indexZeroBased;

    /**
     * Constructs a MarkCommand with the specified 1-based index.
     *
     * @param indexOneBased The 1-based index of the task to be marked as completed.
     */
    public UnmarkCommand(int indexOneBased) {
        this.indexZeroBased = indexOneBased - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ClippyException {
        if (tasks.size() == 0) {
            throw new ClippyException("You have no tasks in the list to unmark.");
        }
        Task t = tasks.get(indexZeroBased);
        t.markAsIncomplete();
        storage.save(tasks.getAll());
        ui.showUnmarked(t);
    }
}
