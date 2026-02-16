package momo.command;

import momo.storage.Storage;
import momo.task.Task;
import momo.task.TaskList;
import momo.task.Todo;
import momo.ui.Ui;

/**
 * Represents a command that adds a {@link Todo} task
 * to the task list.
 */
public class AddTodoCommand implements Command {
    private final String description;

    /**
     * Creates a new {@code AddTodoCommand}.
     *
     * @param description the description of the todo task.
     */
    public AddTodoCommand(String description) {
        assert description != null : "Description must not be null";
        assert !description.trim().isEmpty() : "Description must not be empty";

        this.description = description;
    }

    /**
     * Adds a new {@link Todo} task to the task list.
     * Also saves the updated task list to storage and
     * returns a confirmation message.
     *
     * @param tasks the task list to which the todo is added.
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler that saves the updated task list.
     * @return the confirmation message after successfully adding the todo.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        Task task = new Todo(description);
        tasks.addTask(task);

        assert tasks.size() > 0 : "Task list should not be empty after adding a todo";

        storage.save(tasks);
        return ui.getAddTaskMessage(task, tasks);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as adding a todo does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
