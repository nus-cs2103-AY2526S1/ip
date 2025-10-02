package focus;

/**
 * Represents an executable user command.
 */
public abstract class FocusCommand {

    /**
     * Returns whether this command should exit the application after execution.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns whether this command mutates the task list.
     * Implementations return true for commands that add, delete, or update tasks.
     *
     * @return true if the command changes the task list, false otherwise.
     */
    public boolean isMutating() {
        return false;
    }

    /**
     * Executes this command against the given task list.
     *
     * @param tasks Task list to operate on.
     * @throws FocusException If the command cannot be executed.
     */
    public abstract void execute(TaskList tasks) throws FocusException;

}
