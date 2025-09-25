package heimerdinger.command;

import heimerdinger.HeimerdingerException;
import heimerdinger.Storage;
import heimerdinger.Ui;
import heimerdinger.task.TaskList;
import heimerdinger.task.ToDo;

/**
 * Adds a {@code ToDo} task.
 *
 * <p><b>Usage:</b> {@code todo description}</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * todo keep clothes
 * }</pre>
 *
 * <p>This command validates that a description is present.
 * If the description is blank, a {@link HeimerdingerException}
 * is thrown to signal invalid input.</p>
 *
 * @see ToDo
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddToDoCommand} with the given description.
     *
     * @param description the description of the ToDo task; may not be blank
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a {@link ToDo} task with the stored description,
     * appends it to the {@code tasks}, informs the user via {@code ui},
     * and persists the updated list via {@code storage}.
     *
     * @param tasks   the live task list to mutate; must not be {@code null}
     * @param ui      the UI sink used to display feedback; must not be {@code null}
     * @param storage the persistence layer used to save changes; must not be {@code null}
     * @throws HeimerdingerException if the description is empty or if saving to storage fails
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HeimerdingerException {
        if (description.isEmpty()) {
            throw new HeimerdingerException("I can't read your mind great scientist. "
                    + "ToDo must have an accompanying text!");
        }
        ToDo t = new ToDo(description);
        tasks.add(t);
        ui.showAdded(t);
        storage.save(tasks.getAll());
    }
}
