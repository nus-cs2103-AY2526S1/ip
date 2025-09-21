package hachiware;

/**
 * Represents an abstract command that can be executed by Hachiware.
 * Each command defines its own behavior when executed and whether it signals program exit.
 */
public abstract class Command {

    /**
     * Checks if this command is one that causes the program to exit.
     *
     * @return {@code true} if the command should exit, {@code false} otherwise.
     */
    public abstract boolean isExit();

    /**
     * Executes the command with access to the task list, UI, and storage file.
     *
     * @param tasks The current list of tasks.
     * @param ui For displaying messages.
     * @param storefile The storage handler for saving/loading tasks.
     * @throws HachiwareException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, StoreFile storefile) throws HachiwareException;
}
