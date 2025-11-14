package command;


import exception.BaymaxException;
import storage.Storage;
import task.TaskList;
import task.TaskType;
import task.Todo;
import ui.Ui;

/**
 * Represents a command to add a {@link Todo} task to the task list.
 */
public class TodoCommand extends Command {
    private final String desc;

    /**
     * Creates a new {@code TodoCommand} with the specified description.
     *
     * @param desc The description of the todo task.
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the todo command by creating a {@code Todo} task,
     * adding it to the task list, saving the updated list to storage,
     * and returning a confirmation message.
     *
     * @param tasks   The task list to which the todo task will be added.
     * @param ui      The UI component used to display messages.
     * @param storage The storage component used to save the updated task list.
     * @return A message confirming the task has been added, or an error message if creation fails.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Todo t = new Todo(desc, TaskType.TODO);
            tasks.add(t);
            storage.save(tasks.getAll());
            return ui.showTaskAdded(t, tasks.size());
        } catch (BaymaxException e) {
            return ui.showError(e.getMessage());
        }
    }
}
