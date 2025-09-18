package charsiew.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs over a period of time.
 * An Event has a description (name), a start date, and an end date.
 */
public class Event extends Task {
    /** The start date of the event. */
    protected LocalDate from;
    /** The end date of the event. */
    protected LocalDate to;

    /**
     * Constructs an Event task with a name, start date, and end date.
     *
     * @param name The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date as a LocalDate object.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date as a LocalDate object.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task,
     * including its type, completion status, name, and formatted start and end dates.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
