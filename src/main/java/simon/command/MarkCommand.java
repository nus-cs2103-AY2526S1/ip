package simon.command;

import java.util.ArrayList;

import simon.storage.Storage;
import simon.task.Task;
import simon.task.TaskList;
import simon.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 * A <code>MarkCommand</code> object contains the index of the task to be marked as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done (0-based).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the task at the specified index as done,
     * saving the updated list to storage, and setting the response message.
     *
     * @param tasks The task list containing the task to mark.
     * @param ui The user interface for displaying messages.
     * @param storage The storage system for saving tasks.
     * @throws Exception If an error occurs during marking or saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        Task t = tasks.get(index);
        t.markAsDone();
        storage.save(new ArrayList<>(tasks.getTasks()));
        setString(ui.showMarkTask(t));
    }
}