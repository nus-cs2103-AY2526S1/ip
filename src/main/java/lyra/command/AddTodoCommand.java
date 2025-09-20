package lyra.command;

import lyra.task.TaskList;
import lyra.task.Todo;
import lyra.task.Task;
import lyra.util.Ui;
import lyra.util.Storage;
import lyra.exception.LyraException;

/**
 * Command implementation for adding a new todo task to the task list.
 * Creates a simple task with a description and no deadline.
 */
public class AddTodoCommand extends Command {
    private final String description;
    
    /**
     * Constructs a new AddTodoCommand with the specified description.
     *
     * @param description the description of the todo task to be created
     */
    public AddTodoCommand(String description) {
        assert description != null && !description.trim().isEmpty() : "description must not be null or empty";
        this.description = description;
    }
    
    /**
     * Executes the add todo command by creating a new Todo task,
     * adding it to the task list, saving to storage, and displaying confirmation.
     *
     * @param tasks the task list to add the new todo to
     * @param ui the user interface for displaying confirmation messages
     * @param storage the storage component for persisting the updated task list
     * @throws LyraException if an error occurs during storage operations
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LyraException {
        assert tasks != null : "tasks must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";
        Task task = new Todo(description);
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.getSize());
    }
}
