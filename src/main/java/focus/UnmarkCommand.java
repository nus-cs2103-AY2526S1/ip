package focus;

/**
 * Marks a task as done.
 */
public class UnmarkCommand extends FocusCommand {

    private final int[] userIndexes; // 1-based indexes as typed by user

    /**
     * Constructs an UnmarkCommand.
     *
     * @param userIndexes One-based single or multi-index of the task (inputted by the user)
     *                    to mark.
     */
    public UnmarkCommand(int... userIndexes) {
        this.userIndexes = userIndexes;
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by unmarking the tasks for given userIndexes as done.
     *
     * @param tasks Task list to update.
     */
    @Override
    public void execute(TaskList tasks) throws FocusException {
        tasks.unmarkTasks(this.userIndexes);
    }

}
