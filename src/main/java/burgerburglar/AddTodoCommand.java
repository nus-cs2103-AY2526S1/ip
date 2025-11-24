package burgerburglar;

/**
 * Command to add a Todo task to the task list.
 * <p>
 * A Todo is a simple task with only a description and completion status.
 */
public class AddTodoCommand extends Command {

    private final String args;

    /**
     * Constructs a new AddTodoCommand with the given user input.
     *
     * @param args the description of the Todo task
     */
    public AddTodoCommand(String args) {
        assert args != null : "Arguments for AddTodoCommand cannot be null";
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        guardEmptyDescription(args);

        String description = args.trim();
        Task task = new Todo(description);
        tasks.addTask(task);
        assert tasks.getTasks().contains(task) : "Todo should be added to TaskList";

        storage.save(tasks);
        return "BURGER ADDED: " + task + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).";
    }

    /**
     * Guard clause to check that the description is not empty.
     *
     * @param description the raw input description
     * @throws BurgerException if description is empty
     */
    private void guardEmptyDescription(String description) throws BurgerException {
        if (description == null || description.trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: The description of a todo cannot be empty.");
        }
    }
}
