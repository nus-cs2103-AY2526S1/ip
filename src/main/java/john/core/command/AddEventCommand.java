package john.core.command;

import john.core.exception.ParseException;
import john.model.Event;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

import java.time.LocalDateTime;

/**
 * Command that adds a new Event task to the task list.
 *
 * Responsibilities:
 * - Construct an Event from a description and a start/end date-time.
 * - Append it to the provided TaskList.
 * - Persist the updated list via Storage.
 * - Return a user-facing confirmation message in CommandResult.
 *
 * Assumptions:
 * - The caller has already validated that 'from' is not after 'to'.
 * - 'desc', 'from', and 'to' are non-null.
 *
 * Side effects:
 * - Mutates the given TaskList by adding one task.
 * - Calls storage.save(tasks) to persist state.
 */
public class AddEventCommand implements Command {
    private final String desc;
    private final LocalDateTime from, to;

    /**
     * Creates an AddEventCommand.
     *
     * @param desc event description (non-null, non-empty)
     * @param from start date-time (non-null)
     * @param to   end date-time (non-null, expected to be >= from)
     */
    public AddEventCommand(String desc, LocalDateTime from, LocalDateTime to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command: adds an Event to the list and saves it.
     *
     * @param tasks   the mutable task list to add into
     * @param storage persistence mechanism used to save the updated list
     * @param ui      user interface used for messaging (not used directly here)
     * @return a success result containing a confirmation message
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        if (to.isBefore(from)) {
            throw new ParseException("End time must be after start time.");
        }
        Task t = new Event(desc, from, to);
        tasks.add(t);
        storage.save(tasks);

        return CommandResult.ok(
                "Got it. I've added this task:\n" + t +
                        "\nNow you have " + tasks.size() + " tasks in the list."
        );
    }
}
