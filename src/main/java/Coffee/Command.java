package Coffee;

/**
 * Represents a command in the Coffee application.
 * Each command defines specific behavior that operates on the task list,
 * user interface, and storage.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, user interface, and storage.
     *
     * @param tasks Task list that the command operates on.
     * @param ui User interface for displaying messages.
     * @param storage Storage for saving or loading tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether this command signals the application to exit.
     *
     * @return {@code true} if the command should terminate the application,
     *         {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }

}
