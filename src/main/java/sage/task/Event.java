package sage.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * An Event task has a description, a start time, an end time, and can be marked as done or not done.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     * @param description The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task, including its type icon, start time, and end time.
     * The dates and times are formatted as "MMM dd yyyy, h:mm a".
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a file.
     * The format is: E | <status> | <description> | <from_time> | <to_time>
     * The dates and times are saved in ISO 8601 format.
     * @return A file-formatted string representing the event task.
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
