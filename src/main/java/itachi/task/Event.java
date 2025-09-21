package itachi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start and end date.
 * An event has a description inherited from {@link Task}, a start date and
 * an end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an {@code Event} task with the given description, start date and
     * end date.
     * @param description the description of the event
     * @param from the start date of the event in yyyy-MM-dd format
     * @param to the end date of the event in yyyy-MM-dd format
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null : "Description can't be null";
        assert from != null : "Event start date string can't be null";
        assert to != null : "Event end date string can't be null";
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + "(from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }


    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }
}
