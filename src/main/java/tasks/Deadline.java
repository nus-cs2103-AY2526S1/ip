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
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeUtil.DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
