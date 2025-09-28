package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;
import alpaca.task.Task;

/**
 * Command to mark or unmark a task as done in the task list.
 * Validates the task index and updates the task status accordingly.
 */
public class MarkCommand extends Command {

    private final String arg;
    private final boolean isMark;

    /**
     * Constructs a MarkCommand.
     *
     * @param arg    The task index as a string.
     * @param isMark True to mark the task as done, false to unmark.
     */
    public MarkCommand(String arg, boolean isMark) {
        assert arg != null : "Argument cannot be null";
        this.arg = arg;
        this.isMark = isMark;
    }

    /**
     * Executes the mark/unmark operation on the specified task.
     * Provides clear feedback if the task index is invalid or out of range.
     *
     * @param tasks   The task list to operate on; must not be null.
     * @param ui      The UI for displaying messages; must not be null.
     * @param storage The storage to save task updates; must not be null.
     * @return A message indicating the result of the operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        StringBuilder response = new StringBuilder();

        try {
            int index = Integer.parseInt(arg.trim()) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task t = tasks.get(index);

            if (isMark) {
                t.markAsDone();
                String msg = "Nice! I've marked this task as done:\n  " + t;
                response.append(msg);
                ui.showMessage(msg);
            } else {
                t.markAsNotDone();
                String msg = "OK! I've marked this task as not done yet:\n  " + t;
                response.append(msg);
                ui.showMessage(msg);
            }

            storage.save(tasks.getTasks());

        } catch (NumberFormatException e) {
            String msg = "Invalid task number. Please enter a valid integer.";
            response.append(msg);
            ui.showMessage(msg);
        } catch (IndexOutOfBoundsException e) {
            String msg = "Oops! That task number doesn't exist.";
            response.append(msg);
            ui.showMessage(msg);
        } catch (Exception e) {
            String msg = "An unexpected error occurred while marking the task.";
            response.append(msg);
            ui.showMessage(msg);
        }

        return response.toString();
    }
}
