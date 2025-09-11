package john.core.command;

import john.model.Deadline;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

import java.time.LocalDateTime;

/**
 * Command that adds a new Deadline task to the task list.
 * <p>
 * Responsibilities:
 * - Construct a Deadline from a description and due date-time.
 * - Append it to the provided TaskList.
 * - Persist the updated list via Storage.
 * - Return a user-facing confirmation message in CommandResult.
 * <p>
 * Side effects:
 * - Mutates the given TaskList by adding one task.
 * - Calls storage.save(tasks) to persist state.
 */
public class AddDeadlineCommand implements Command {
    private final String desc;
    private final LocalDateTime by;

    /**
     * Creates an AddDeadlineCommand.
     *
     * @param desc non-null, non-empty task description
     * @param by   non-null due date-time for the deadline
     */
    public AddDeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    /**
     * Executes the command: adds a Deadline to the list and saves it.
     *
     * @param tasks   the mutable task list to add into
     * @param storage persistence mechanism used to save the updated list
     * @param ui      user interface used for messaging (not used directly here)
     * @return a success result containing a confirmation message
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = new Deadline(desc, by);
        tasks.add(t);
        storage.save(tasks);
        return CommandResult.ok("Got it. I've added this task:\n" + t +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}