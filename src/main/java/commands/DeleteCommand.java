package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        System.out.println("oki! i've removed this task:\n  " + removedTask +
                "\nnow you have " + tasks.size() + " tasks left!");
        ui.showLine();
    }
}
