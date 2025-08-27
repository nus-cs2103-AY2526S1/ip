package tasks;

import exception.RainyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs during a time interval (from start to end).
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with a description and time range provided as strings.
     *
     * @param description the task description
     * @param from the start time as a string
     * @param to the end time as a string
     * @throws RainyException if the date/time format is invalid
     */
    public Event(String description, String from, String to) throws RainyException {
        super(description, TaskType.EVENT);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * Constructs an Event task with a description and time range.
     *
     * @param description the task description
     * @param from the start time
     * @param to the end time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    private LocalDateTime parseDateTime(String input) throws RainyException {
        DateTimeFormatter[] formatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")
        };

        for (DateTimeFormatter f : formatters) {
            try {
                return LocalDateTime.parse(input.trim(), f);
            } catch (Exception ignored) {}
        }
        throw new RainyException("oh no!!! wrong date format... please use yyyy-MM-dd HHmm or d/M/yyyy HHmm.");
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");
        return super.toString() + " (from: " + from.format(displayFormat)
                + " to: " + to.format(displayFormat) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
