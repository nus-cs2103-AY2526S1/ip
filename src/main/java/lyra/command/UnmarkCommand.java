package lyra.command;

import lyra.task.TaskList;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Command implementation for marking a task as not done in the task list.
 * Marks the specified task as incomplete and updates the storage.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;
    
    /**
     * Constructs a new UnmarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to mark as not done (1-based indexing)
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    /**
     * Executes the unmark command by marking the specified task as not done,
     * saving the updated task list to storage, and displaying confirmation.
     *
     * @param tasks the task list containing the task to be unmarked
     * @param ui the user interface for displaying confirmation messages
     * @param storage the storage component for persisting the updated task list
     * @throws LyraException if the task index is invalid or an error occurs during storage operations
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        assert tasks != null : "tasks must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";
        assert taskIndex >= 0 && taskIndex < tasks.getSize() : "taskIndex must be in range";
        tasks.markTaskAsNotDone(taskIndex);
        storage.save(tasks.getTasks());
        ui.showTaskMarked(tasks.getTasks().get(taskIndex), false);
    }
}
