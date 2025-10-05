package sage.command;

import java.time.LocalDateTime;

import sage.task.Event;

/**
 * Represents a command to add an Event task.
 */
public class AddEventCommand extends AddCommand {
    /**
     * Constructs an AddEventCommand with the given description, start time, and end time for the Event task.
     * @param description The description of the Event task.
     * @param from The start date and time of the Event task.
     * @param to The end date and time of the Event task.
     */
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        super(new Event(description, from, to));
    }
}