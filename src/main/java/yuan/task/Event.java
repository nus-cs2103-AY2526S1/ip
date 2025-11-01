package yuan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task scheduled between a start and an end date.
 *
 * <p>AI-Assisted: JavaDoc suggested by ChatGPT and refined manually.</p>
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Creates an Event task with the given description, start time, end time, and status.
     *
     * @param description
     * @param start
     * @param end
     * @param isDone
     */
    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(FORMATTER) + " to: " + endTime.format(FORMATTER) + ")";
    }

    /**
     * Converts the event task into a storage-friendly format.
     *
     * @return storage string representation
     */
    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + startTime + " | " + endTime;
    }
}
