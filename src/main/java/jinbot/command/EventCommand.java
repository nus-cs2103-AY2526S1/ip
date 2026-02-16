package jinbot.command;

import java.time.LocalDate;

import jinbot.task.Event;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand implements Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an EventCommand with the given description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public EventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;

        assert !to.isBefore(from) : "Event end date must not be before from";
        this.to = to;
    }

    /**
     * Executes the event command by creating a new Event task,
     * adding it to the task list, and displaying a confirmation message.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The task list to which the event is added.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task event = new Event(description, from, to);

        int beforeSize = taskList.getSize();
        taskList.addTask(event);
        // Tasklist size should be increased by 1
        assert taskList.getSize() == beforeSize + 1
            : "Tasks list size mismatch";

        String response = "Got it. I've added this task:\n  " + event
            + "\nNow you have " + taskList.getSize()
            + " tasks in the list.";

        ui.printBox(response);
        return response;
    }
}
