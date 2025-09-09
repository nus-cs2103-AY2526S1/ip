package commands;

import exception.RainyException;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that adds a {@link Deadline} task to the task list.
 * The {@code AddDeadlineCommand} creates a new deadline task with a
 * description and due date, adds it to the list, saves the updated list
 * to storage, and notifies the user through the UI.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;
    private int addedTaskIndex = -1;

    /**
     * Creates a new {@code AddDeadlineCommand} with the given description
     * and due date.
     *
     * @param description the description of the deadline task
     * @param by          the due date/time of the deadline task
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(Object... args) throws RainyException {
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];

        Task t = new Deadline(description, by);
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
            message = "hmm... nothing to undo for this deadline.";
            return;
        }
        TaskList tasks = (TaskList) args[0];
        Ui ui = (Ui) args[1];
        Storage storage = (Storage) args[2];

        Task removed = tasks.deleteTask(addedTaskIndex);
        storage.save(tasks.getAllTasks());

        ui.showLine();
        message = "undo oki! removed this deadline:\n  " + removed
                + "\nnow you have " + tasks.size() + " tasks left!";
        ui.showLine();
    }
}
