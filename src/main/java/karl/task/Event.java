package karl.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event type task with start and end dates.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates an Event task with description and start/end dates.
     * @param description task description
     * @param from start date yyyy-MM-dd
     * @param to end date yyyy-MM-dd
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from.trim(), INPUT_FORMAT);
        this.to = LocalDate.parse(to.trim(), INPUT_FORMAT);
    }

    /**
     * Returns the start date.
     * @return LocalDate of start date
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date.
     * @return LocalDate of end date
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns string representation including event dates.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(OUTPUT_FORMAT) +
                " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
