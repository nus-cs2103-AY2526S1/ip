package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that adds an {@link Event} task to the task list.
 * The {@code AddEventCommand} creates a new event task with a description,
 * start time, and end time, adds it to the list, saves the updated list
 * to storage, and notifies the user through the UI.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates a new {@code AddEventCommand} with the given description,
     * start time, and end time.
     *
     * @param description the description of the event task
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
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
        System.out.println("oki! i've added this task:\n  " + t
                + "\nnow you have " + tasks.size() + " tasks left!");
        ui.showLine();
    }
}
