package bobbywasabi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a description, marked status, start time, and end time.
 * Extends the generic Task class.
 */
public class Event extends bobbywasabi.tasks.Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event with description, marked status, start time, and end time.
     *
     * @param description The description of the event.
     * @param isMarked    Whether the event is marked as done.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, boolean isMarked, LocalDateTime start, LocalDateTime end) {
        super(description, isMarked);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representing the event duration.
     *
     * @return The duration string in the format "(from:start to:end)".
     */
    public String getDuration() {
        String formattedStart = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        String formattedEnd = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return String.format("(from: %s to: %s)",
                formattedStart,
                formattedEnd);
    }

    /**
     * Returns the string representation of the Event task.
     * Prepends "[E]" to indicate the task type, appends the duration.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.getDuration();
    }

    /**
     * Returns a formatted string of the event data suitable for saving.
     * Format: E|description|checked_status|start|end
     *
     * @return A pipe-separated string representing the Event task.
     */
    @Override
    public String getData() {
        String startOutput = this.start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String endOutput = this.end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.format("E|%s|%s|%s|%s",
                super.getDescription(), super.getMarkedStatus(), startOutput, endOutput);
    }
}
