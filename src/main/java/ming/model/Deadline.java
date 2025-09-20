package ming.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a deadline task with a description and a due date & time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the deadline task.
     * @param by          Due date and time of the deadline task.
     */
    public Deadline(String description, LocalDateTime by, List<String> tags) {
        super(description, tags);
        this.by = by;
    }

    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
