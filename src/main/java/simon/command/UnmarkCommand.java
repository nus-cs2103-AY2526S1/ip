package simon.command;

import java.util.ArrayList;

import simon.storage.Storage;
import simon.task.Task;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to unmark a task as not completed.
 * An <code>UnmarkCommand</code> object contains the index of the task to be marked as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as not done (0-based).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done,
     * saving the updated list to storage, and setting the response message.
     *
     * @param tasks The task list containing the task to unmark.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     * @throws Exception If an error occurs during unmarking or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task t = tasks.get(index);
        t.markAsNotDone();
        storage.save(new ArrayList<>(tasks.getTasks()));
        setString(ui.showUnmarkTask(t));
    }
}