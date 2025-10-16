package junny.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import junny.TaskTypes;

/**
 * Represents a task that occurs over a period of time.
 * An {@code Event} stores a description, a start date, and an end date.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an {@code Event} with the given description, start date, and end date.
     *
     * @param description The description of the task.
     * @param start The start date of the event in {@code yyyy-MM-dd} format.
     * @param end The end date of the event in {@code yyyy-MM-dd} format.
     */
    public Event(String description, String start, String end) {
        super(description, TaskTypes.EVENT);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Returns the start date of the event.
     *
     * @return The {@code LocalDate} representing the start date.
     */
    public LocalDate getStart() {
        return this.start;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The {@code LocalDate} representing the end date.
     */
    public LocalDate getEnd() {
        return this.end;
    }

    /**
     * Returns a string representation of the event for display.
     *
     * @return The formatted string showing type, status, description, and dates.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
        return "[E]" + super.toString() + " (from: " + start.format(outputFormat)
                + " to: " + end.format(outputFormat) + ")";
    }

    /**
     * Returns a string representation of the event for file storage.
     *
     * @return A string in the format {@code E | status | description | start to end}.
     */
    @Override
    public String toFileString() {
        int status;
        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }
        return "E | " + status + " | " + this.description + " | " + this.start + " to " + this.end;
    }
}
