package hachiware;

/**
 * Represents a command that marks a task in the task list as not done.
 * <p>
 * The {@code UnmarkCommand} sets the completion status of the task at the
 * specified index to not done, updates the storage, and returns a confirmation message.
 * </p>
 */
public class UnmarkCommand extends Command {
    /** Message displayed when a task is successfully unmarked. */
    private static final String SUCCESS_MESSAGE = "OK, I've marked this task as not done yet:\n";

    /** The zero-based index of the task to unmark. */
    private final int index;

    /**
     * Constructs an {@code UnmarkCommand} for the task specified by the user input.
     * <p>
     * The input argument is expected to be a 1-based task number as provided by the user.
     * It is converted to a zero-based index for internal use.
     * </p>
     *
     * @param arg the 1-based task number entered by the user
     * @throws HachiwareException if the argument is not a valid integer
     */
    public UnmarkCommand(String arg) throws HachiwareException {
        try {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new HachiwareException("MEOW! Invalid task number.");
        }
    }

    /**
     * Executes the unmark command by marking the task at the specified index as not done,
     * saving the updated task list, and returning a confirmation message.
     *
     * @param tasks   the task list containing the task to unmark
     * @param ui      the user interface (unused in this command)
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message indicating the task has been unmarked
     * @throws HachiwareException if the index is out of bounds or saving fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "StoreFile cannot be null";

        if (index < 0 || index >= tasks.size()) {
            throw new HachiwareException("Hachiware.Hachiware.Task number out of bounds");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        storage.save(tasks.getAll());
        return SUCCESS_MESSAGE + task;
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since unmarking a task does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
