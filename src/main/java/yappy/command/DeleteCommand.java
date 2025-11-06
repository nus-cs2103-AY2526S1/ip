package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

/**
 * Represents a command to delete a task from the TaskList.
 * A <code>DeleteCommand</code> object has a Task field representing the task to be added.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a new {@code DeleteCommand} object with the specified Task.
     * This constructor initializes the DeleteCommand's Task.
     *
     * @param index Index of the task to be deleted from the TaskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete a task at position <code>index</code> in the TaskList.
     *
     * @param tasks The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @throws YappyException If there are no tasks or the wrong index is given.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        Task removedTask = tasks.delete(index);
        return ("Alrighty I have removed the following task:\n\t   " + removedTask.toString());
    }
}
