package john.core.command;

import john.core.exception.ParseException;
import john.model.Task;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

/**
 * Command that deletes a task by its 1-based position in the list.
 * <p>
 * Responsibilities:
 * - Validate the provided 1-based index against the current list size.
 * - Remove the targeted task when valid.
 * - Persist the updated list via Storage.
 * - Return a user-facing confirmation or validation message.
 * <p>
 * Behavior on invalid input:
 * - If the index is out of range, no changes are made, and a helpful message is returned.
 * <p>
 * Side effects:
 * - Mutates the given TaskList by removing one task (when valid).
 * - Calls storage.save(tasks) to persist state.
 */
public class DeleteCommand implements Command {
    private final int oneBased;

    /**
     * Creates a DeleteCommand.
     *
     * @param oneBased the task number as shown to the user (1-based)
     */
    public DeleteCommand(int oneBased) {
        this.oneBased = oneBased;
    }

    /**
     * Executes the command: deletes the task at the given 1-based index and saves the list.
     *
     * @param tasks   the mutable task list
     * @param storage persistence backend used to save the updated list
     * @param ui      user interface (not used directly here)
     * @return a CommandResult with either a confirmation or a validation message
     * @throws ParseException kept for interface contract; not thrown by this implementation
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        int idx = oneBased - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return CommandResult.ok("Please provide a valid task number between 1 and "
                    + tasks.size() + ".");
        }
        Task removed = tasks.remove(idx);
        storage.save(tasks);
        return CommandResult.ok("Noted. I've removed this task:\n"
                + removed + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
