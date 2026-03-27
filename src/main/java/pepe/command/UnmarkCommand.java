package pepe.command;

import java.io.IOException;
import java.util.ArrayList;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

/**
 * Command to unmark a task in the task list as not done.
 * <p>
 * Extends the {@link Command} abstract class. When executed, it
 * marks the specified task as incomplete, updates the UI, and
 * saves the updated task list to storage.
 */
public class UnmarkCommand extends Command {

    private final int[] indices;

    /**
     * Creates a new UnmarkCommand for the task at the given index.
     *
     * @param indices the zero-based index of the task to unmark
     */
    public UnmarkCommand(int[] indices) {
        this.indices = indices;
    }

    /**
     * Executes the Unmark command.
     * <p>
     * Marks the task as not done, shows feedback on the UI, and saves
     * the updated task list to storage.
     *
     * @param tasks   the current task list
     * @param ui      the UI object to show feedback
     * @param storage the storage object to save updated tasks
     * @throws PepeExceptions if the index is invalid or an I/O error occurs during saving
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PepeExceptions {
        try {
            ArrayList<Task> unmarkedTasks = tasks.unmarkTasks(this.indices);
            super.setResponse(ui.showUiUnmark(unmarkedTasks.toArray(new Task[0])));
            storage.save(tasks);
        } catch (IOException e) {
            throw new PepeExceptions("Error saving file: " + e.getMessage());
        }
    }
}
