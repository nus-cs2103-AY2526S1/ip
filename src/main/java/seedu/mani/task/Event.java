package seedu.mani.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a specific from date and to date, representing an event.
 */
public class Event extends Task {
    private static final String TYPE = "E";
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm", Locale.ENGLISH);
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy ha", Locale.ENGLISH);

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, from and to date string.
     *
     * @param task Description of the event; must not be null or empty
     * @param from Start date-time string in format d/MM/yyyy HHmm
     * @param to End date-time string in format d/MM/yyyy HHmm
     */
    public Event(String task, String from, String to) {
        super(task);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        assert this.from.isBefore(this.to) : "Event 'from' must be before 'to'";
    }

    /**
     * Constructs a new Event task with completion status.
     *
     * @param task Description of the event; must not be null or empty
     * @param from Start date-time string in format d/MM/yyyy HHmm
     * @param to End date-time string in format d/MM/yyyy HHmm
     * @param mark True if task is completed, false otherwise
     */
    public Event(String task, String from, String to, boolean mark, int count) {
        super(task, mark, count);
        this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        assert this.from.isBefore(this.to) : "Event 'from' must be before 'to'";
    }

    @Override
    public String getDetails() {
        return TYPE + " | " + super.getDetails() + " | " + this.from.format(INPUT_FORMATTER) + " | " + this.to.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() +
                " (from: " + this.from.format(OUTPUT_FORMATTER) +
                " to: " + this.to.format(OUTPUT_FORMATTER) + ")";
    }
}