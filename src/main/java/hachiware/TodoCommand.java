package hachiware;

/**
 * Represents a command that adds a {@link ToDo} task to the task list.
 * <p>
 * The {@code TodoCommand} validates the provided description, creates a new ToDo task,
 * adds it to the {@link TaskList}, saves the updated list to storage, and returns a confirmation message.
 * </p>
 */
public class TodoCommand extends Command {
    /** The description of the todo task. */
    private final String description;

    /**
     * Constructs a {@code TodoCommand} with the given description.
     *
     * @param description the description of the todo task
     * @throws HachiwareException if the description is empty
     */
    public TodoCommand(String description) throws HachiwareException {
        if (description.isEmpty()) {
            throw new HachiwareException("MEOW!!! OI The description of a todo cannot be empty.");
        }
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new {@link ToDo} task,
     * adding it to the task list, saving the updated list, and returning a confirmation message.
     *
     * @param tasks   the task list to add the new task to
     * @param ui      the user interface used to display the confirmation message
     * @param storage the storage handler used to save the updated task list
     * @return a confirmation message indicating the task has been added
     * @throws HachiwareException if saving the updated task list fails
     */
    @Override
    public String execute(TaskList tasks, Ui ui, StoreFile storage) throws HachiwareException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "StoreFile cannot be null";

        Task task = new ToDo(description);
        tasks.add(task);
        storage.save(tasks.getAll());
        return ui.showAddTaskMessage(task, tasks.size());
    }

    /**
     * Indicates whether this command will cause the program to exit.
     *
     * @return {@code false}, since adding a todo task does not terminate the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
