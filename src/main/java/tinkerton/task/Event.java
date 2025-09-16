package tinkerton.task;

import tinkerton.util.Date;
import tinkerton.core.TinkertonException;
import java.time.LocalDateTime;

/**
 * Represents an Event task with a name, completion status, start date/time, and end date/time.
 */
public class Event extends Task {
    /** The start time string of the event. */
    private String start;
    /** The end time string of the event. */
    private String end;
    /** The start date of the event. */
    private Date startDate;
    /** The end date of the event. */
    private Date endDate;

    /**
     * Constructs an Event task.
     *
     * @param name The name of the event.
     * @param isCompleted True if the event is completed, false otherwise.
     * @param start The start time as a string.
     * @param end The end time as a string.
     * @throws TinkertonException If the event ends before it starts or starts in the past.
     */
    public Event(String name, boolean isCompleted, String start, String end)
            throws TinkertonException {
        super(name, isCompleted);
        this.start = start;
        this.end = end;
        this.startDate = new Date(start);
        this.endDate = new Date(end);

        if (endDate.date().isBefore(startDate.date())) {
            throw new TinkertonException(
                    "Event that ends before it starts? Doesn't make too much sense right...");
        } else if (startDate.date().isBefore(LocalDateTime.now())) {
            throw new TinkertonException(
                    "Event that already started in the past? A bit too late to add that...");
        }
    }

    /**
     * Returns the string representation of the Event task for display.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (from: %s to: %s)", this.startDate, this.endDate);
    }

    /**
     * Returns the string representation of the Event task for file storage.
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "E | " + completed + " | " + this.name() + " | " + this.start + " | " + this.end;
    }

    /**
     * Checks if the Event task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the event starts on the given date, false otherwise.
     */
    @Override
    public boolean onDate(Date date) {
        return date.date().toLocalDate().isEqual(this.startDate.date().toLocalDate());
    }
}
