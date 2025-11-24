package cheryl.task;

import java.time.LocalDate;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a new Event task.
     *
     * @param title The title of the event
     * @param from  The start date of the event
     * @param to    The end date of the event
     */
    public Event(String title, LocalDate from, LocalDate to) {
        super(title);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Event dates cannot be null");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time string
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time string
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String in the format "[E][ ] title (from: MMM d yyyy to: MMM d yyyy)"
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}