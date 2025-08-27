package commands;

import exception.RainyException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        tasks.markTask(index);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        System.out.println("yay! :D i've marked this task as done:\n  " + tasks.getTask(index));
        ui.showLine();
    }
}
