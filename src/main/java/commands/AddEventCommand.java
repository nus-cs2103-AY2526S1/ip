package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RainyException {
        Task t = new Event(description, from, to);
        tasks.addTask(t);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        System.out.println("oki! i've added this task:\n  " + t +
                "\nnow you have " + tasks.size() + " tasks left!");
        ui.showLine();
    }
}
