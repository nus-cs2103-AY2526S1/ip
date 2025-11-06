package v.command;

import v.storage.Storage;
import v.task.DukeException;
import v.task.Task;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by removing the task at the given index,
     * displaying feedback, and persisting the updated list.
     *
     * @param tasks   The task list to update.
     * @param ui      The UI to display feedback.
     * @param storage The storage used to persist changes.
     * @return False to continue running the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.remove(index);
            ui.showLine();
            System.out.println("     Very well. I've removed this task from the records:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " task"
                    + (tasks.size() != 1 ? "s" : "") + " in the list.");
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("No such task exists. The number must be between 1 and " + tasks.size());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
