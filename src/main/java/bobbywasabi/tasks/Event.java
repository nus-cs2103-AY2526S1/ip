package bobbywasabi.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task with a description, completion status, start time, and end time.
 * <p>
 * Extends the generic {@link Task} class and provides methods to format the event's
 * duration and convert the event into a string suitable for display or file storage.
 */
public class Event extends bobbywasabi.tasks.Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an {@code Event} with a description, marked status, start time, and end time.
     *
     * @param description The description of the event; must not be null or empty.
     * @param isMarked    {@code true} if the event is marked as done, {@code false} otherwise.
     * @param start       The start time of the event; must not be null.
     * @param end         The end time of the event; must not be null and must not be before {@code start}.
     */
    public Event(String description, boolean isMarked, LocalDateTime start, LocalDateTime end) {
        super(description, isMarked);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representing the event's duration.
     * <p>
     * The start and end times are formatted using the pattern "MMM d yyyy HHmm"
     * (e.g., "Aug 13 2025 1430").
     *
     * @return A string in the format "(from: start to: end)" representing the event duration.
     */
    public String getDuration() {
        String formattedStart = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        String formattedEnd = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        return String.format("(from: %s to: %s)",
                formattedStart,
                formattedEnd);
    }

    /**
     * Returns the string representation of this {@code Event} for display purposes.
     * <p>
     * Prepends "[E]" to indicate the task type, appends the formatted duration,
     * and includes the description and completion status inherited from {@link Task}.
     *
     * @return A string representation of the event suitable for console display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + this.getDuration();
    }

    /**
     * Returns a formatted string of the event's data suitable for persistent storage.
     * <p>
     * Format: {@code E|description|marked_status|start|end}, where:
     * <ul>
     *     <li>{@code marked_status} is "[X]" if done, "[ ]" otherwise.</li>
     *     <li>{@code start} and {@code end} are formatted using "d/M/yyyy HHmm" (e.g., "13/8/2025 1430").</li>
     * </ul>
     *
     * @return A pipe-separated string representing the event task for saving to file.
     */
    @Override
    public String getData() {
        String startOutput = this.start.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String endOutput = this.end.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.format("E|%s|%s|%s|%s",
                super.getDescription(), super.getMarkedStatus(), startOutput, endOutput);
    }
}
