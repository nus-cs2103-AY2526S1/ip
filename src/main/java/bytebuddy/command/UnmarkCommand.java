package bytebuddy.command;

import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;
import bytebuddy.storage.Storage;

/**
 * Represents a command that marks a task as not done in the {@link TaskList}.
 * <p>
 * The task to unmark is identified by its index in the list. If the index is
 * invalid, an error message is returned instead.
 */
public class UnmarkCommand extends Command {

    /** The index of the task to mark as not done (0-based). */
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} for the specified task index.
     *
     * @param taskIndex the index of the task to mark as not done
     */
    public UnmarkCommand(int taskIndex) {
        super(false);
        this.index = taskIndex;
    }

    /**
     * Executes the unmark command.
     * <p>
     * Marks the task at the specified index as not done. If the index is invalid,
     * returns an error message. Uses the {@link Ui} to format feedback messages.
     *
     * @param storage the storage manager (not modified by this command)
     * @param tasks   the current task list
     * @param ui      the user interface for generating messages
     * @return a string message confirming the task was unmarked, or an error message
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (index >= 0 && index < tasks.getSize()) {
            Task t = tasks.getTask(index);
            assert t != null : "Task should not be null";
            t.markAsNotDone();
            return ui.getMarkedMessage(t, false);
        }
        return ui.getErrorMessage("Error: Invalid task index.");
    }
}
