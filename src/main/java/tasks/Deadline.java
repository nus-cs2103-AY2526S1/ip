package tasks;

import java.time.LocalDateTime;

import exception.RainyException;
import util.DateTimeUtil;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and a due date string.
     *
     * @param description the task description
     * @param by the due date as a string
     * @throws RainyException if the date cannot be parsed
     */
    public Deadline(String description, String by) throws RainyException {
        super(description, TaskType.DEADLINE);
        this.by = DateTimeUtil.parse(by);
    }

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description the task description
     * @param by the due date as a {@link LocalDateTime}
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
        assert this.by != null : "Deadline 'by' must not be null";
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] "
                + description
                + " (by: " + DateTimeUtil.formatForDisplay(by) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
