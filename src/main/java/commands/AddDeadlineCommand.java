package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        Task t = new Deadline(description, by);
        tasks.addTask(t);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        System.out.println("oki! i've added this task:\n  " + t +
                "\nnow you have " + tasks.size() + " tasks left!");
        ui.showLine();
    }
}
