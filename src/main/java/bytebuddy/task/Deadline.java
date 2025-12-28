package bytebuddy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in ByteBuddy.
 * A  Deadline has a description, a completion status,
 * and a due date by which it must be completed.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a Deadline with the given description and due date string.
     *
     * @param description Description of the task.
     * @param by Due date in the format 'yyyy-MM-dd'.
     * @param isDone true if the task is already completed; false otherwise.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, inputFormat);
    }

    /**
     * Creates a Deadline with the given description and due date.
     *
     * @param description Description of the task.
     * @param by Due date as a LocalDate.
     * @param isDone true if the task is already completed; false otherwise.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends the deadline date formatted as 'MMM d yyyy'.
     */
    @Override
    public String toString() {
        return super.toString() + " | " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}