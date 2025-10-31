package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a task with a specific due date.
 * <p>
 * A {@code Deadline} extends {@link Task} by adding a {@link LocalDate}
 * field to record when the task must be completed.
 * </p>
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a new {@code Deadline} task.
     *
     * @param name   the task description
     * @param marked whether the task is marked as completed
     * @param id     the task’s unique identifier
     * @param by     the due date of the task
     */
    public Deadline(String name, boolean marked, int id, LocalDate by) {
        super(name, marked, id);
        this.by = by;
    }

    /**
     * Returns the due date of this deadline.
     *
     * @return the {@link LocalDate} representing when the task is due
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a user-readable string representation of the deadline.
     *
     * @return formatted string in the form {@code [D][X] task_name (by: yyyy-mm-dd)}
     */
    @Override
    public String toString() {
        return "[D][" + getMarked() + "] " + getName() + "(by: " + getBy() + ")";
    }

    /**
     * Returns the storage format of this deadline for saving to file.
     *
     * @return pipe-delimited string in the form {@code D|X|task_name|yyyy-mm-dd}
     */
    @Override
    public String toDataFormat() {
        return "D|" + getMarked() + "|" + getName() + "|" + getBy();
    }
}
