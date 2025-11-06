package v.command;

import v.storage.Storage;
import v.task.DukeException;
import v.task.Task;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new MarkCommand.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        // Assertion: index should be non-negative
        assert index >= 0 : "Index should be non-negative";
        this.index = index;
    }

    /**
     * Executes this command by marking the task at the given index as done,
     * displaying feedback, and persisting the updated list.
     *
     * @param tasks   The task list containing the task to mark.
     * @param ui      The UI to display feedback to the user.
     * @param storage The storage used to persist the updated task list.
     * @return False to continue running the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        // Assertion: parameters should not be null
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        try {
            Task task = tasks.markAsDone(index);
            // Assertion: task should not be null after marking
            assert task != null : "Marked task should not be null";
            ui.showLine();
            System.out.println("     A tick for triumph. Marked as done:");
            System.out.println("       " + task);
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            ui.showError("An error occurred while marking the task as done.");
        }
        return false;
    }
}
