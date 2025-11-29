package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * Adds a {@link Deadline} task parsed from user input.
 * Expects input in the form {@code description /by <time>}.
 */
public class AddDeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Creates a command to add a deadline task.
     *
     * @param description Description of the task.
     * @param by          Due date/time in a parseable string format.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to create a {@link Deadline}.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException If the input is invalid or the date/time cannot be parsed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description == null || description.isBlank() || by == null || by.isBlank()) {
            throw new DukeException("Invalid deadline format! Use: deadline <task> /by <time>.");
        }
        try {
            final Task task = new Deadline(description, by);
            tasks.add(task);
            ui.show(tasks.addedMessage(task));
            storage.save(tasks);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Unrecognized date/time for deadline: " + by + ".", e);
        }
    }
}
