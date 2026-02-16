package momo.command;

import java.time.LocalDateTime;

import momo.storage.Storage;
import momo.task.Event;
import momo.task.Task;
import momo.task.TaskList;
import momo.ui.Ui;

/**
 * Represents a command that adds an {@link Event} task
 * to the task list.
 */
public class AddEventCommand implements Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new {@code AddEventCommand}.
     *
     * @param description the description of the event task.
     * @param from the start date and time of the event.
     * @param to the end date and time of the event.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        assert description != null : "Description must not be null";
        assert !description.trim().isEmpty() : "Description must not be empty";
        assert from != null : "Start time must not be null";
        assert to != null : "End time must not be null";
        assert from.isBefore(to) : "Start time must be before end time";

        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds a new {@link Event} task to the task list.
     * Also saves the updated task list to storage and
     * returns a confirmation message.
     *
     * @param tasks the task list to which the event is added.
     * @param ui the user interface used to generate messages.
     * @param storage the storage handler that saves the updated task list.
     * @return the confirmation message after successfully adding the event.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";

        Task task = new Event(description, from, to);
        tasks.addTask(task);

        assert tasks.size() > 0 : "Task list should not be empty after adding an event";

        storage.save(tasks);
        return ui.getAddTaskMessage(task, tasks);
    }

    /**
     * Returns whether this command will exit the program.
     *
     * @return {@code false}, as adding an event does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
