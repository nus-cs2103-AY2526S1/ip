package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a {@link Task} in the task list as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new {@code MarkCommand} for the task at the specified index.
     *
     * @param index The index (0-based) of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the specified task as done,
     * saving the updated task list to storage, and returning a confirmation message.
     *
     * @param tasks   The task list containing the task to be marked.
     * @param ui      The UI component used to display the confirmation message.
     * @param storage The storage component used to save the updated task list.
     * @return A message confirming the task has been marked as done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.get(index);
        t.markAsDone();
        storage.save(tasks.getAll());
        return ui.showTaskMarked(t);
    }
}
