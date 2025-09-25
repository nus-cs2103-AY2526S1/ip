package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.Task;
import heimerdinger.task.TaskList;

/**
 * Deletes a single task by its 1-based index in the task list.
 *
 * <p><b>Usage:</b> {@code delete <index>}</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * delete 1
 * }</pre>
 *
 * <p>This command validates that the provided argument is a number
 * and that the number corresponds to an existing task. If the index
 * is invalid or out of range, a {@link HeimerdingerException} is thrown.</p>
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code DeleteCommand} for the specified index.
     *
     * @param arg the raw string argument representing the task index (1-based)
     * @throws HeimerdingerException if the argument is not numeric
     */
    public DeleteCommand(String arg) throws HeimerdingerException {
        if (isNumeric(arg.trim())) {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } else {
            throw new HeimerdingerException("My precise machinations require a number!");
        }
    }

    /**
     * Removes the task at the given index, informs the user, and persists the updated list.
     *
     * @param tasks   the live task list to mutate; must not be {@code null}
     * @param ui      the UI sink used to display feedback; must not be {@code null}
     * @param storage the persistence layer used to save changes; must not be {@code null}
     * @throws HeimerdingerException if the index is out of bounds or saving fails
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException {
        if (index < 0 || index >= tasks.size()) {
            throw new HeimerdingerException("My calculations indicate that your number is not accessible!");
        }
        Task deleted = tasks.remove(index);
        ui.showDeleted(deleted, tasks.size());
        storage.save(tasks.getAll());
    }
}
