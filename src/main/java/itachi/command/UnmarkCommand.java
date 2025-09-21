package itachi.command;

import java.io.IOException;

import itachi.ItachiException;
import itachi.Storage;
import itachi.Ui;
import itachi.task.Task;
import itachi.task.TaskList;

/**
 * Represents a command to unmark a task in the task list, marking it
 * as not done.
 */
public class UnmarkCommand extends Command {
    private int index;
    private boolean wasDone;

    /**
     * Creates an UnmarkCommand for the inputted task index.
     *
     * @param index the 0-based index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as not done, saving the updated
     * task list to storage, and displaying confirmation via the UI.
     *
     * @param tasks the TaskList containing all tasks
     * @param ui the UI instance used to display output
     * @param storage the Storage instance used to persist tasks
     * @throws ItachiException if any errors related to Itachi operations occur
     * @throws IOException if there is an I/O error while accessing storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        assert tasks != null : "Task list should never be null";
        assert index >= 0 && index < tasks.size() : "Index out of bounds: " + index;
        if (index < 0 || index >= tasks.size()) {
            ui.showLine();
            ui.showMessage("No such task number");
            ui.showLine();
            return;
        }

        Task task = tasks.get(this.index);
        wasDone = task.isDone();
        task.markAsNotDone();
        storage.save(tasks.getTasks());
        ui.showLine();
        ui.showMessage("Nice! I've marked this task as NOT done:");
        ui.showMessage(task.toString());
        ui.showLine();
        if (this.isUndoable()) {
            CommandHistory.getInstance().push(this);
        }
    }
    /**
     * Undoes the effect of an UnmarkCommand by restoring the previous task state.
     *
     * If the task was previously marked as done, this method re-marks it as done.
     * The updated task list is then saved to storage and a confirmation message
     * is displayed via the UI.
     *
     * @param tasks the TaskList containing all current tasks
     * @param ui the UI instance used to display messages
     * @param storage the Storage instance used to persist changes
     * @throws ItachiException if any task-specific error occurs
     * @throws IOException if there is an error saving tasks to storage
     */
    @Override
    public void undo(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        Task task = tasks.getTasks().get(index);
        if (wasDone) {
            task.markAsDone();
            storage.save(tasks.getTasks());
            ui.showMessage("Undo Unmark: " + task);
        }
    }
}


