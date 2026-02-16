package momo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date and time.
 * Extends {@link Task} and adds a {@code by} field to indicate when the task is due.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new {@code Deadline} task with the given description and due date/time.
     *
     * @param description the description of the task.
     * @param by the due date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new {@code Deadline} task with the given description, due date/time,
     * and completion status.
     *
     * @param description the description of the task.
     * @param by the due date and time of the task.
     * @param isDone whether the task is marked as done.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the due date and time of the task.
     *
     * @return the {@link LocalDateTime} representing the due date/time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, description, completion status, and due date/time.
     *
     * @return a formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Converts the deadline task to a string format suitable for saving to disk.
     *
     * @return a string in the format {@code "D | <status> | <description> | <yyyy-MM-dd HHmm>"}.
     */
    @Override
    public String convertToSaveFormat() {
        return "D | " + super.convertToSaveFormat()
                + String.format(" | %s", by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
