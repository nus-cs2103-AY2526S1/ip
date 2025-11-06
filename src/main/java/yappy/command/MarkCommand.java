package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

/**
 * Represents a command to mark a task as completed.
 * A <code>MarkCommand</code> object has a Task field representing the task to be marked as completed.
 *
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a new {@code MarkCommand} object with the specified Task.
     * This constructor initializes the MarkCommand's Task.
     *
     * @param index Index of the task to be marked as completed in the TaskList
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command to mark the specified task as completed.
     *
     * @param tasks The tasks which have been added to Yappy.
     * @param ui The ui object that controls interactions with a user.
     * @param storage The storage object that handles storing to and reading from disk.
     * @throws YappyException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        Task markedTask = tasks.mark(index);
        return ("Alrighty I have marked this task as completed:\n\t   " + markedTask.toString());
    }
}
