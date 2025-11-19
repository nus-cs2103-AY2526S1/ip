package goober.task;

import java.time.LocalDateTime;

import goober.helper.Parser;

/**
 * Represents a task with a due date/time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline.
     *
     * @param description the description
     * @param by the by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateTimeToString(by) + ")";
    }
}
