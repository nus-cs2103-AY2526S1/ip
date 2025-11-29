package seedu.duke.command;

import java.time.LocalDateTime;

import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.util.DateTimeUtil;

/**
 * Adds an {@link Event} task parsed from user input.
 * Expects input in the form {@code description /from <start> /to <end>}.
 */
public class AddEventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates a command to add an event.
     *
     * @param description Description of the event.
     * @param from        Start date/time in a parseable string format.
     * @param to          End date/time in a parseable string format.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command to create an {@link Event}.
     *
     * @param tasks   The task list.
     * @param ui      UI for output.
     * @param storage Persistent storage.
     * @throws DukeException If input is invalid or times are malformed or out of order.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description == null || description.isBlank()
                || from == null || from.isBlank()
                || to == null || to.isBlank()) {
            throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>.");
        }
        try {
            final LocalDateTime start = DateTimeUtil.parseFlexibleDateOrDateTime(from);
            final LocalDateTime end = DateTimeUtil.parseFlexibleDateOrDateTime(to);
            if (end.isBefore(start)) {
                throw new DukeException("Event end time must be after start time.");
            }

            final Task task = new Event(description, from, to);
            tasks.add(task);
            ui.show(tasks.addedMessage(task));
            storage.save(tasks);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Unrecognized date/time for event: " + e.getMessage() + ".", e);
        }
    }
}
