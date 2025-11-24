package burgerburglar;

/**
 * Represents a general command in BurgerBurglar.
 * <p>
 * Each concrete command should implement the {@link #execute(TaskList, Ui, Storage)} method,
 * which performs the action associated with the command.
 * <p>
 * The {@link #isExit()} method indicates whether this command should terminate the program.
 */
public abstract class Command {

    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param tasks   the task list to operate on
     * @param ui      the UI instance to interact with the user
     * @param storage the storage instance for saving/loading tasks
     * @return a string message to be shown to the user
     * @throws BurgerException if execution fails
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException;

    /**
     * Indicates whether this command signals program termination.
     *
     * @return true if this command exits the program, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
