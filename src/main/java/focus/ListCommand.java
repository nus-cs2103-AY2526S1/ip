package focus;

/**
 * Lists all tasks currently in the task list.
 */
public class ListCommand extends FocusCommand {

    /**
     * Executes the list command by printing all tasks.
     *
     * @param tasks Task list to display.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.printTaskList();
    }

}
