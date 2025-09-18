package joobot.task;

import joobot.main.DateTimeValue;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    private final DateTimeValue by;

    /**
     * Constructs a new {@code Deadline} task with a description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the task (string format).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTimeValue(by);
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns a string representation of this deadline task for display.
     *
     * @return The formatted deadline task string.
     */
    @Override
    public String toString() {
        return getTypeIcon() + super.toString()
                + " (by: " + by.toDisplayString() + ")";
    }

    /**
     * Returns the file format string for this deadline task.
     *
     * @return The string in file storage format: "D | status | description | by".
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1" : "0")
                + " | " + getDescription()
                + " | " + by.toFileString();
    }
}
