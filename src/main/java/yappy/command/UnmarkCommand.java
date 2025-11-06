package yappy.command;

import yappy.backend.Storage;
import yappy.backend.TaskList;
import yappy.exception.YappyException;
import yappy.task.Task;
import yappy.ui.Ui;

/**
 * Represents a command to mark a task as uncompleted.
 * An <code>UnmarkCommand</code> object has a Task field representing the task to be marked as uncompleted.
 *
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs a new {@code UnmarkCommand} object with the specified Task.
     * This constructor initializes the UnmarkCommand's Task.
     *
     * @param index Index of the task to be marked as uncompleted in the TaskList
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws YappyException {
        Task unmarkedTask = tasks.unmark(index);
        return ("Alrighty I have marked this task as uncompleted:\n\t   " + unmarkedTask.toString());
    }
}
