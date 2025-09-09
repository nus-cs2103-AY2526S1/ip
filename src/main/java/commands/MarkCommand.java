package commands;

import exception.RainyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that marks a task in the task list as done.
 * The {@code MarkCommand} updates the task's status, saves the change
 * to storage, and notifies the user through the UI.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];
        tasks.markTask(index);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "yay! :D i've marked this task as done:\n  " + tasks.getTask(index);
        ui.showLine();
    }

    @Override
    public void undo(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];
        tasks.unmarkTask(index);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "undo oki! unmarked this task:\n  " + tasks.getTask(index);
        ui.showLine();
    }
}
