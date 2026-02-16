package jinbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description,
 * a start date, and an end date.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an Event task with the specified description,
     * start date, and end date.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of this event task
     * in the format used for saving to disk.
     *
     * @return A save format string for this event task.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (getIsDone() ? "1" : "0")
            + " | " + getTaskName()
            + " | " + from.toString() + " ~ " + to.toString() + " | " + getPriority();
    }

    /**
     * Returns the string representation of this event task for display.
     *
     * @return The string containing the task type, status, description, and date range.
     */
    @Override public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(formatter)
            + " to: " + to.format(formatter) + ")" + " [" + getPriority() + "]";
    }
}
