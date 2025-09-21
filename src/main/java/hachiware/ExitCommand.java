package hachiware;

/**
 * Represents a command that exits the program.
 * <p>
 * The {@code ExitCommand} does not modify the task list or storage.
 * It simply returns a farewell message and signals that the program should terminate.
 * </p>
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by returning a farewell message.
     * <p>
     * This command does not modify the task list or save to storage.
     * </p>
     *
     * @param tasks    the current task list (unused in this command)
     * @param ui       the user interface (unused in this command)
     * @param storeFile the storage handler (unused in this command)
     * @return the farewell message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storeFile) {

        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
