package bytebuddy.command;

import bytebuddy.storage.Storage;
import bytebuddy.task.Task;
import bytebuddy.task.TaskList;
import bytebuddy.ui.Ui;

/**
 * Represents a command that marks a task as done in the {@link TaskList}.
 * <p>
 * The task to mark is identified by its index in the list. If the index is
 * invalid, an error message is returned instead.
 */
public class MarkCommand extends Command {

    /** The index of the task to mark as done (0-based). */
    private final int index;

    /**
     * Constructs a {@code MarkCommand} for the specified task index.
     *
     * @param taskIndex the index of the task to mark as done
     */
    public MarkCommand(int taskIndex) {
        super(false);
        this.index = taskIndex;
    }

    /**
     * Executes the mark command.
     * <p>
     * Marks the task at the specified index as done. If the index is invalid,
     * returns an error message. Uses the {@link Ui} to format feedback messages.
     *
     * @param storage the storage manager (not modified by this command)
     * @param tasks   the current task list
     * @param ui      the user interface for generating messages
     * @return a string message confirming the task was marked, or an error message
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        if (index >= 0 && index < tasks.getSize()) {
            Task t = tasks.getTask(index);
            assert t != null : "Task should not be null";
            t.markAsDone();
            return ui.getMarkedMessage(t, true);
        }
        return ui.getErrorMessage("Error: Invalid task index.");
    }
}
