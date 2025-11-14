package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a {@link Task} in the task list as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a new {@code UnmarkCommand} for the task at the specified index.
     *
     * @param index The index (0-based) of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by marking the specified task as not done,
     * saving the updated task list to storage, and returning a confirmation message.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The UI component used to display the confirmation message.
     * @param storage The storage component used to save the updated task list.
     * @return A message confirming the task has been unmarked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.get(index);
        t.markAsUndone();
        storage.save(tasks.getAll());
        return ui.showTaskUnmarked(t);
    }
}

