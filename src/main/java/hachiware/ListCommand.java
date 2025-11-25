package hachiware;

/**
 * Represents a command that displays all tasks in the task list.
 * <p>
 * The {@code ListCommand} retrieves tasks from the {@link TaskList},
 * sorts them by priority, and returns a formatted string showing all tasks.
 * If the task list is empty, it returns a corresponding message.
 * </p>
 */
public class ListCommand extends Command {
    /** Message displayed when the task list is empty. */
    private static final String TASK_LIST_EMPTY = "Your task list is empty!";

    /** Header for displaying the list of tasks. */
    private static final String TASK_LIST_HEADER = "Here are the tasks in your list:\n";

    /**
     * Executes the list command by returning all tasks in the task list
     * formatted as a numbered list sorted by priority.
     *
     * @param tasks   the task list to display
     * @param ui      the user interface (unused in this command)
     * @param storage the storage handler (unused in this command)
     * @return a formatted string of all tasks, or a message if the list is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) {
        assert tasks != null : "TaskList cannot be null";

        if (tasks.size() == 0) {
            return TASK_LIST_EMPTY;
        }

        tasks.getAll().sort((t1, t2) -> {
            return t1.getPriority().ordinal() - t2.getPriority().ordinal();
        });

        StringBuilder sb = new StringBuilder(TASK_LIST_HEADER);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since listing tasks does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
