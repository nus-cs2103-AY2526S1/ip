package dwight.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a specified time range. An {@code Event} includes a
 * description, a start date, and an end date.
 */
public class Event extends Task<Event> {

    /** The start date of the event. */
    private LocalDate from;

    /** The end date of the event. */
    private LocalDate to;

    /**
     * Creates a new {@code Event} task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);

        assert from != null : "Event start date cannot be null.";
        assert to != null : "Event end date cannot be null.";
        assert !from.isAfter(to) : "Event start date cannot be after the end date.";

        this.from = from;
        this.to = to;
    }

    @Override
    public String getUniqueKey() {
        return "E:" + super.getUniqueKey() + ":" + this.from + ":" + this.to;
    }

    /**
     * Returns a string representation of this event task for display purposes. The string includes
     * the task type identifier {@code [E]}, the description, and the formatted start and end dates.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        String fromStr = "from: " + this.from.format(formatter);
        String toStr = "to: " + this.to.format(formatter);
        return "[E]" + super.toString() + " (" + fromStr + " " + toStr + ")";
    }

    /**
     * Returns a serialized representation of this event task suitable for saving to storage. The
     * format begins with the task type identifier {@code E}, followed by the description,
     * completion status, and the start and end dates.
     *
     * @return The serialized string of the event task.
     */
    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String fromStr = " | " + this.from.format(formatter);
        String toStr = " | " + this.to.format(formatter);
        return "E | " + super.serialize() + fromStr + toStr;
    }
}
