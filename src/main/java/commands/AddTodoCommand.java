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
    private int addedTaskIndex = -1;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];
        Task t = new Todo(description);
        tasks.addTask(t);
        addedTaskIndex = tasks.size() - 1;
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "oki! i've added this task:\n  " + t
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }

    @Override
    public void undo(Object... args) throws RainyException {
        if (addedTaskIndex == -1) {
            message = "hmm... nothing to undo for this todo.";
            return;
        }

        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];

        Task removed = tasks.deleteTask(addedTaskIndex);
        storage.save(tasks.getAllTasks());

        ui.showLine();
        message = "undo oki! removed this task:\n  " + removed
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }
}
