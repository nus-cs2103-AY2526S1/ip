package kane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} has a description and a specific date and time by which it should be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Creates a new {@code Deadline} task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date and time in {@code yyyy-MM-dd HHmm} format (e.g. {@code 2019-12-02 1800}).
     * @throws IllegalArgumentException If the {@code by} string is not in the correct format.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);

        assert description != null : "Description cannot be null for a Deadline.";
        assert by != null : "Due date string cannot be null for a Deadline.";

        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Please use yyyy-MM-dd HHmm, e.g. 2019-12-02 1800.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by.format(INPUT_FORMAT);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return this.by.equals(other.by);
    }
}

