package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;
import seedu.duke.ui.Ui;

/**
 * Adds a {@link ToDo} task.
 */
public class AddTodoCommand extends Command {

    private final String description;

    /**
     * Creates a command to add a todo task.
     *
     * @param description Description of the task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add a {@link ToDo}.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException Never thrown here, but kept for symmetry.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        final Task task = new ToDo(description);
        tasks.add(task);
        ui.show(tasks.addedMessage(task));
        storage.save(tasks);
    }
}
