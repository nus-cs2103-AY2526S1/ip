package merlie.command;

import java.time.LocalDateTime;

import merlie.task.Event;

/**
 * Adds an Event task to the task list.
 */
public class EventCommand extends AddCommand {
    /**
     * Constructs an EventCommand for the specified Event task.
     *
     * @param description Description of the task.
     * @param from Start date (and time) of the event.
     * @param hasFromTime Whether the start includes a specific time.
     * @param to End date (and time) of the event.
     * @param hasToTime Whether the end includes a specific time.
     */
    public EventCommand(String description, LocalDateTime from,
            boolean hasFromTime, LocalDateTime to, boolean hasToTime) {
        super(new Event(description, from, hasFromTime, to, hasToTime));
    }
}
