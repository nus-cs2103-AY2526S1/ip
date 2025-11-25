package hachiware;
//Used AI to write javadoc

/**
 * Represents a command that adds a new {@link Task} to the task list.
 * <p>
 * The {@code AddCommand} is responsible for:
 * <ul>
 *   <li>Adding the specified task to the provided {@link TaskList}.</li>
 *   <li>Saving the updated task list to persistent storage via {@link StoreFile}.</li>
 *   <li>Returning a confirmation message through the {@link Ui}.</li>
 * </ul>
 */
public class AddCommand extends Command {
    /** The task to be added. */
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task.
     *
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the given task list,
     * saving the updated list to storage, and returning a confirmation message.
     *
     * @param tasks   the task list to add the task to
     * @param ui      the user interface that formats the confirmation message
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message indicating the task has been added
     * @throws HachiwareException if saving to storage fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {

        tasks.add(task);
        storage.save(tasks.getAll());
        return ui.showAddTaskMessage(task, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
