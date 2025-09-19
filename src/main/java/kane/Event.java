package kane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs within a specific time range.
 * An {@code Event} has a description, a start date and time, and an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates a new {@code Event} with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start date and time in {@code yyyy-MM-dd HHmm} format (e.g. {@code 2019-12-02 1800}).
     * @param to          The end date and time in {@code yyyy-MM-dd HHmm} format.
     * @throws IllegalArgumentException If {@code from} or {@code to} are not in the correct format.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Please use yyyy-MM-dd HHmm, e.g. 2019-12-02 1800.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), from.format(OUTPUT_FORMAT), to.format(OUTPUT_FORMAT));
    }

    @Override
    public String toFileString() {
        return String.format("%s | %s | %s", super.toFileString(), from.format(INPUT_FORMAT), to.format(INPUT_FORMAT));
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Event other = (Event) obj;
        return this.from.equals(other.from) && this.to.equals(other.to);
    }
}
