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
    private int addedTaskIndex = -1;

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
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];
        Task t = new Event(description, from, to);
        tasks.addTask(t);
        storage.save(tasks.getAllTasks());
        ui.showLine();
        message = "oki! i've added this task:\n  " + t
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }

    @Override
    public void undo(Object... args) throws RainyException {
        if (addedTaskIndex == -1) {
            message = "hmm... nothing to undo for this event.";
            return;
        }
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];

        Task removed = tasks.deleteTask(addedTaskIndex);
        storage.save(tasks.getAllTasks());

        ui.showLine();
        message = "undo oki! removed this event:\n  " + removed
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }
}
