package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;

/**
 * Abstract base class for all user commands in the Alpaca application.
 * Each command defines its behavior by implementing the {@link #execute} method.
 * Subclasses may override {@link #isExit()} to signal the program should terminate.
 */
public abstract class Command {

    /**
     * Executes the command.
     * Implementations should handle any exceptions internally to prevent
     * the program from crashing.
     *
     * @param tasks   The task list to operate on; never null.
     * @param ui      The UI handler for displaying messages; never null.
     * @param storage The storage handler for saving/loading tasks; never null.
     * @return A message to display to the user reflecting the command outcome.
     * @throws AssertionError if any of the arguments are null.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if this command should terminate the program.
     * Default implementation returns false; override in exit commands.
     *
     * @return true if the command signals the program to exit; false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Helper method for subclasses to assert non-null arguments.
     *
     * @param obj The object to check.
     * @param name The name of the argument (for error messages).
     */
    protected void assertNotNull(Object obj, String name) {
        assert obj != null : name + " cannot be null";
    }
}
