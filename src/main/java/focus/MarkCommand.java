package focus;

/**
 * Marks a task as done.
 */
public class MarkCommand extends FocusCommand {

    private final int[] userIndexes; // 1-based indexes as typed by user

    /**
     * Constructs a MarkCommand.
     *
     * @param userIndexes One-based single or multi-index of the task (inputted by the user)
     *                    to mark.
     */
    public MarkCommand(int... userIndexes) {
        this.userIndexes = userIndexes;
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by marking the tasks for given userIndexes as done.
     *
     * @param tasks Task list to update.
     */
    @Override
    public void execute(TaskList tasks) throws FocusException {
        tasks.markTasks(this.userIndexes);
    }

}
