package aries.task;

import java.time.LocalDateTime;

import aries.util.DateTime;

/**
 * Represents a deadline task with a description and a due date/time.
 */
public class Deadline extends Task {
    private static final long serialVersionUID = 1L;

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and due date/time.
     *
     * @param description The description of the deadline task.
     * @param by The due date/time in string format.
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null : "Due date/time cannot be null";
        assert !by.isEmpty() : "Due date/time cannot be empty";
        this.by = DateTime.parse(by);
    }

    @Override
    public String getKey() {
        return "D:" + norm(description) + ":" + DateTime.format(by);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + DateTime.format(by) + ")";
    }
}
