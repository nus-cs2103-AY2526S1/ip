package hachiware;
//AI helped in generating javadoc comments

/**
 * Represents a command that deletes a task from the task list.
 * <p>
 * The {@code DeleteCommand} removes the task at the specified index,
 * updates the persistent storage, and returns a confirmation message.
 * </p>
 */
public class DeleteCommand extends Command {
    /** The zero-based index of the task to be deleted. */
    private final int index;


    /**
     * Constructs a {@code DeleteCommand} with the given task number.
     * <p>
     * The input argument is expected to be a 1-based task number as provided by the user.
     * It is converted to a zero-based index for internal use.
     * </p>
     *
     * @param arg the 1-based task number entered by the user
     * @throws HachiwareException if the argument is not a valid integer
     */
    public DeleteCommand(String arg) throws HachiwareException {
        try {
            this.index = Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new HachiwareException("MEOW! Invalid task number.");
        }
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list, saving the updated list, and returning a confirmation message.
     *
     * @param tasks   the task list containing the task to delete
     * @param ui      the user interface that formats the confirmation message
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message indicating the task has been deleted
     * @throws HachiwareException if the index is out of bounds or saving fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "StoreFile cannot be null";

        if (index < 0 || index >= tasks.size()) {
            throw new HachiwareException("Hachiware.Hachiware.Task number out of bounds");
        }
        Task removed = tasks.delete(index);
        storage.save(tasks.getAll());
        return ui.showDeleteTaskMessage(removed, tasks.size());
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since deleting a task does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
