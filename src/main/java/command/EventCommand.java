package command;

import task.Event;
import task.Task;
import tasklist.TaskList;
import ui.UI;

/**
 * Represents a command to create a new event task.
 * Adds the event to the task list and displays confirmation.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs an EventCommand with the given description and time period.
     *
     * @param description the description of the event task
     * @param from        the start time/date of the event
     * @param to          the end time/date of the event
     */
    public EventCommand(String description, String from, String to) {
        super(CommandType.CREATE_EVENT);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     * Creates a new event task, adds it to the task list,
     * and displays confirmation messages.
     *
     * @param taskList the task list to which the event will be added
     */
    @Override
    public String execute(TaskList taskList) {
        Task event = new Event(description, from, to);
        taskList.addTask(event);
        return UI.showMessage("Got it. I've added this task:")
                + UI.showMessage(event.toString())
                + UI.showMessage("Now you have " + taskList.size() + " tasks in the list.");
    }
}
