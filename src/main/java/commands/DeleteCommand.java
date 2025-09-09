package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that deletes a task from the task list.
 * The {@code DeleteCommand} removes the task at the specified index,
 * saves the updated list to storage, and notifies the user through the UI.
 */
public class DeleteCommand extends Command {
    private final int index;
    private Task deletedTask;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];
        deletedTask = tasks.deleteTask(index);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "oki! i've removed this task:\n  " + deletedTask
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }

    @Override
    public void undo(Object... args) throws RainyException {
        if (deletedTask == null) {
            message = "hmm... nothing to undo for this delete.";
            return;
        }
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];
        tasks.getAllTasks().add(index, deletedTask);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "undo oki! restored this task:\n  " + deletedTask
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
        deletedTask = null;
    }
}
