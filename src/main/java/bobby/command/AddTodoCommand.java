package bobby.command;

import bobby.bobbyexception.BobbyException;
import bobby.task.TaskList;
import bobby.ui.Ui;
import bobby.task.ToDo;
import bobby.task.Task;
import bobby.storage.Storage;

/**
 * Command to add a new todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified task description.
     *
     * @param description Description of the todo task to add.
     */
    public AddTodoCommand(String description) {
        assert description != null : "Description should not be null";
        this.description = description;
    }

    /**
     * Executes the add todo command, adding the new task and saving changes.
     *
     * @param tasks The current list of tasks.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws BobbyException If an error occurs during task creation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task todo = new ToDo(description);
        tasks.add(todo);
        StringBuilder response = new StringBuilder();
        response.append("Added: ").append(todo.toString()).append(System.lineSeparator());
        response.append("Now you have ").append(tasks.size()).append(" tasks in the list.");
        try {
            storage.saveTasks(tasks.getAll());
        } catch (Exception e) {
            response.append(System.lineSeparator())
                    .append("Failed to save tasks fam: ").append(e.getMessage());
        }
        return response.toString();
    }
}
