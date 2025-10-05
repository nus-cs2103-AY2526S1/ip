package sage.command;

import sage.exception.SageException;
import sage.storage.Storage;
import sage.task.Task;
import sage.task.TaskList;
import sage.task.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     * @param taskIndex The zero-based index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command.
     * Deletes the task at the specified index from the TaskList,
     * saves the updated task list to storage, and displays a confirmation message.
     * @param tasks The TaskList to operate on.
     * @param ui The Ui to display messages to the user.
     * @param storage The Storage to save the updated task list.
     * @throws SageException If the task index is invalid.
     */
    @Override
    public String executeAndReturn(TaskList tasks, Ui ui, Storage storage) throws SageException {
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        return ui.showMessageAndReturn("Noted. I've removed this task:\n   " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
