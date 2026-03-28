package clippy.command;

import clippy.ClippyException;
import clippy.storage.Storage;
import clippy.task.Task;
import clippy.task.TaskList;
import clippy.task.ToDoTask;
import clippy.ui.Ui;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description == null
                                        ? ""
                                        : description.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ClippyException {
        if (description.isEmpty()) {
            throw new ClippyException("Oops! You need to provide a description for a todo.");
        }
        Task t = new ToDoTask(description);
        tasks.add(t);
        storage.save(tasks.getAll());
        ui.showAdded(t, tasks.size());
    }
}
