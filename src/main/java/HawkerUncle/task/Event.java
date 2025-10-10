package HawkerUncle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, which has a description, a status, a starting and ending time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initializes the task with a description, a start time, an end time, and completion status
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone The status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     * @return The LocalDateTime representing the start time of the event.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Retrieves the end time of the event.
     * @return The LocalDateTime representing the end time of the event.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Converts the Event task to a string representation for displaying to the user.
     * The format is: [E] description (from: start time to: end time).
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Converts the Event task to a format suitable for saving to storage.
     * The format is: E | isDone | description | from | to.
     * @return A string int eh format suitable for saving the task to storage.
     */
    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
