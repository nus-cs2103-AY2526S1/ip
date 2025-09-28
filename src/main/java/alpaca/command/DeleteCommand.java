package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;
import alpaca.task.Task;

/**
 * Command to delete a task from the task list.
 * Parses the task index from user input, removes the task, updates storage,
 * and returns a message reflecting the operation.
 */
public class DeleteCommand extends Command {

    private final String arg;

    /**
     * Constructs a DeleteCommand with the given argument.
     *
     * @param arg The index of the task to delete as a string.
     */
    public DeleteCommand(String arg) {
        assert arg != null : "Argument cannot be null";
        this.arg = arg;
    }

    /**
     * Executes the delete operation.
     * If the task index is invalid or out of bounds, returns an informative message.
     *
     * @param tasks   The task list to operate on; must not be null.
     * @param ui      The UI for displaying messages; must not be null.
     * @param storage The storage to save changes; must not be null.
     * @return A message indicating success or describing the error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        try {
            int index = Integer.parseInt(arg.trim()) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task removed = tasks.remove(index);
            storage.save(tasks.getTasks());

            String msg = "Noted. I've removed this task:\n  " + removed +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
            ui.showMessage(msg);
            return msg;

        } catch (NumberFormatException e) {
            String msg = "Invalid task number. Please enter a valid integer.";
            ui.showMessage(msg);
            return msg;

        } catch (IndexOutOfBoundsException e) {
            String msg = "Oops! That task number doesn't exist.";
            ui.showMessage(msg);
            return msg;

        } catch (Exception e) {
            String msg = "An unexpected error occurred while deleting the task.";
            ui.showMessage(msg);
            return msg;
        }
    }
}
