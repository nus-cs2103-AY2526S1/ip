package john.core.command;

import john.core.exception.ParseException;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;

/**
 * Command that marks a task as not completed by its 1-based position in the list.
 * <p>
 * Responsibilities:
 * - Validate the provided 1-based index against the current list size.
 * - Mark the referenced task as incomplete when valid.
 * - Persist the updated list via Storage.
 * - Return a user-facing confirmation or validation message.
 * <p>
 * Behavior on invalid input:
 * - If the index is out of range, no changes are made and a helpful message is returned.
 * <p>
 * Side effects:
 * - Mutates the given TaskList by marking one task incomplete (when valid).
 * - Calls storage.save(tasks) to persist state.
 */
public class UnmarkCommand implements Command {
    private final int oneBased;

    /**
     * Creates an UnmarkCommand.
     *
     * @param oneBased the task number as shown to the user (1-based)
     */
    public UnmarkCommand(int oneBased) {
        this.oneBased = oneBased;
    }

    /**
     * Executes the command: marks the task at the given 1-based index as incomplete and saves the list.
     *
     * @param tasks   the mutable task list
     * @param storage persistence backend used to save the updated list
     * @param ui      user interface (not used directly here)
     * @return a CommandResult with either a confirmation or a validation message
     * @throws ParseException kept for interface contract; not thrown by this implementation
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage, Ui ui) throws ParseException {
        Integer idx = toZeroBasedOrNull(oneBased, tasks.size());
        if (idx == null) {
            return CommandResult.ok("Please provide a valid task number between 1 and " + tasks.size() + ".");
        }
        tasks.get(idx).markAsIncomplete();
        storage.save(tasks);
        return CommandResult.ok("OK, I've marked this task as not done yet:\n" + tasks.get(idx));
    }

    /**
     * Converts a 1-based index to a 0-based index, or returns null if out of bounds.
     *
     * @param oneBased 1-based position as seen by the user
     * @param size     current number of tasks
     * @return zero-based index if valid; otherwise null
     */
    private static Integer toZeroBasedOrNull(int oneBased, int size) {
        int z = oneBased - 1;
        return (z >= 0 && z < size) ? z : null;
    }
}