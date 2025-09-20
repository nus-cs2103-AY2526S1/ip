package lyra.command;

import lyra.task.TaskList;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Command implementation for deleting a task from the task list.
 * Removes the specified task and updates the storage.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;
    
    /**
     * Constructs a new DeleteCommand for the specified task index.
     *
     * @param taskIndex the index of the task to delete (1-based indexing)
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated task list to storage, and displaying confirmation.
     *
     * @param tasks the task list containing the task to be deleted
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
        Task deletedTask = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(deletedTask, tasks.getSize());
    }
}
