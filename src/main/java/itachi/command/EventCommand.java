package itachi.command;

import java.io.IOException;

import itachi.ItachiException;
import itachi.Storage;
import itachi.Ui;
import itachi.task.Event;
import itachi.task.TaskList;

/**
 * A command that adds an Event task to the task list.
 * <p>
 * The command stores the event in the given TaskList
 * and updates the Storage. It also displays confirmation
 * messages via the Ui.
 */
public class EventCommand extends Command {

    /** Description of the event. */
    private String description;

    /** Start date of the event. */
    private String from;

    /** End date of the event */
    private String to;

    /**
     * Constructs an EventCommand with the
     * given description, start, and end times.
     * @param description Description of the event
     * @param from Start date of the event
     * @param to End date of the event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by creating an Event task, adding it to the task list,
     * saving the updated list to storage, and printing confirmation messages.
     *
     * @param tasks   TaskList to which the event will be added
     * @param ui      Ui to display messages
     * @param storage Storage to save updated tasks
     * @throws ItachiException if an error occurs during addition of task
     * @throws IOException     if saving the task list fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        assert tasks != null : "Task list should never be null";
        Event event = new Event(this.description, this.from, this.to);
        tasks.add(event);
        storage.save(tasks.getTasks());
        ui.showLine();
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(event.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list!");
        ui.showLine();
        if (this.isUndoable()) {
            CommandHistory.getInstance().push(this);
        }
    }

    @Override
    public void undo(TaskList tasks, Ui ui, Storage storage) throws ItachiException, IOException {
        tasks.remove(tasks.size() - 1);
        storage.save(tasks.getTasks());
        ui.showMessage("Undo Event: " + description);
    }
}
