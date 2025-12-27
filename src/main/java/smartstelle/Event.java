package smartstelle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 * An Event task is a type of Task that has a name, completion status, a start
 * date/time, and an end date/time.
 * It extends the Task class and adds functionality to store and format event
 * duration.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /**
     * Constructs a new Event task with start and end date/time, a name, and
     * completion status.
     *
     * @param start       The start date/time as a string in ISO_LOCAL_DATE_TIME
     *                    format (yyyy-MM-ddTHH:mm).
     * @param end         The end date/time as a string in ISO_LOCAL_DATE_TIME
     *                    format (yyyy-MM-ddTHH:mm).
     * @param name        The name or description of the event.
     * @param isCompleted Whether the event is completed or not.
     * @throws StelleException If the task name is invalid or empty.
     */
    public Event(String start, String end, String name, boolean isCompleted) throws StelleException {
        super(name, isCompleted);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
    }

    /**
     * Returns a formatted string representing the start and end date/time of the
     * event.
     * Format pattern: "from dd MMM yyyy HH:mm to dd MMM yyyy HH:mm"
     *
     * @return Formatted start and end date/time string.
     */
    private String formatDate() {
        return "from " + this.start.format(DISPLAY_FORMAT) + " to "
                + this.end.format(DISPLAY_FORMAT);
    }

    /**
     * Returns a display string representing the Event task for UI or console
     * output.
     * Example format: "[E][ ] EventName from 01 Sep 2025 10:00 to 01 Sep 2025
     * 12:00"
     *
     * @return The formatted string for display.
     */
    @Override
    public String display() {
        return "[E]" + super.display() + " " + this.formatDate();
    }

    /**
     * Returns a string representation of the Event task for saving to a file.
     * Format: "E | 0 | EventName | 2025-09-01T10:00 | 2025-09-01T12:00"
     *
     * @return The string representation suitable for storage.
     */
    public String toSaveFormat() {
        return "E | " + (this.getIsCompleted() ? "1" : "0")
                + " | " + this.getName() + " | " + this.start + " | " + this.end;
    }
}
