package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.Task;
import heimerdinger.task.TaskList;

/**
 * Marks a single task as done.
 *
 * <p><b>Usage:</b> {@code mark &lt;index&gt;}</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * mark 2
 * }</pre>
 *
 * <p>This command validates that the argument is a number
 * and corresponds to a valid task index. Indices are 1-based in user
 * commands but stored internally as 0-based. If the index is invalid
 * or out of bounds, a {@link HeimerdingerException} is thrown.</p>
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a {@code MarkCommand} for the given index.
     *
     * @param arg the raw string argument representing the task index (1-based)
     * @throws HeimerdingerException if the argument is not numeric
     */
    public MarkCommand(String arg) throws HeimerdingerException {
        if (isNumeric(arg.trim())) {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } else {
            throw new HeimerdingerException("My precise machinations require a number!");
        }
    }

    /**
     * Marks the task at the given index as done, informs the user, and persists the update.
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
        Task marked = tasks.get(index);
        marked.markAsDone();
        ui.showMarked(marked, tasks);
        storage.save(tasks.getAll());
    }
}
