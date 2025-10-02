package focus;

/**
 * Adds a new ToDo task to the task list.
 */
public class TodoCommand extends FocusCommand {

    private final String description;

    /**
     * Constructs a ToDo Command
     *
     * @param description Description of the ToDo to add.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isMutating() {
        return true;
    }

    /**
     * Executes the command by adding the ToDo to the list and showing feedback.
     *
     * @param tasks Task list to update.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(new ToDo(description), false);
    }

}
