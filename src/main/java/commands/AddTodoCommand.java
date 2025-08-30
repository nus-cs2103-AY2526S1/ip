package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;
import ui.Ui;

/**
 * Represents a command that adds a {@link Todo} task to the task list.
 * The {@code AddTodoCommand} creates a new to-do task with a description,
 * adds it to the list, saves the updated list to storage, and notifies
 * the user through the UI.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        Task t = new Todo(description);
        tasks.addTask(t);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "oki! i've added this task:\n  " + t
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }
}
