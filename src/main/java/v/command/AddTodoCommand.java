package v.command;

import v.storage.Storage;
import v.task.DukeException;
import v.task.Task;
import v.task.TaskList;
import v.task.Todo;
import v.ui.Ui;

/**
 * Command to add a new todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates a new AddTodoCommand.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes this command by creating a new {@code Todo} and adding it to the list.
     * Also persists the updated task list using {@code Storage}.
     *
     * @param tasks   The task list to mutate.
     * @param ui      The UI to display messages.
     * @param storage The storage to persist changes.
     * @return False to indicate the application should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Todo(description);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
