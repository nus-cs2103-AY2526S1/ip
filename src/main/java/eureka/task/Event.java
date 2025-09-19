package eureka.task;

import java.time.LocalDateTime;

/**
 * Represents an event task with start and end times.
 * An event is a task that occurs during a specific time period.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a new Event with the specified start time, end time, task name, and completion status.
     *
     * @param start the start date and time of the event
     * @param end the end date and time of the event
     * @param taskName the name/description of the event
     * @param isDone whether the event is marked as completed
     */
    public Event(LocalDateTime start, LocalDateTime end, String taskName, boolean isDone) {
        super(taskName, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String fromStr = (start != null) ? super.dateTimeFormatter(start) : "N/A";
        String toStr = (end != null) ? super.dateTimeFormatter(end) : "N/A";

        return "[E] " + super.toString()
                + " (from: " + fromStr
                + " to: " + toStr + ")";
    }

    @Override
    public String serialise() {
        return "E | " + super.serialise() + " | " + this.start + " | " + this.end;
    }
}
