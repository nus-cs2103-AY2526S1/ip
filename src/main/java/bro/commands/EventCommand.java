package bro.commands;

import bro.tasks.Event;
import bro.tasks.Tasks;
import bro.utils.FileIo;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private String description;
    private String fromDate;
    private String toDate;

    /**
     * Creates a new EventCommand with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param fromDate The start date of the event task.
     * @param toDate The end date of the event task.
     */
    public EventCommand(String description, String fromDate, String toDate) {
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        Event event = new Event(description, fromDate, toDate);
        String output = tasks.addTask(event);
        FileIo.addEntry(event.toDataString() + "\n");
        return output;
    }
}
