package focus;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends FocusCommand {

    private final int userIndex;

    /**
     * Constructs a Delete Command.
     *
     * @param userIndex One-based index of the task to delete.
     */
    public DeleteCommand(int userIndex) {
        this.userIndex = userIndex;
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by deleting the specified task.
     *
     * @param tasks Task list to update.
     * @throws FocusException If the index is out of range.
     */
    @Override
    public void execute(TaskList tasks) throws FocusException {
        int i = userIndex - 1;
        tasks.deleteTask(i);
    }

}
