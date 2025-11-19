package Coffee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Coffee application.
 * A deadline task has a description, a completion status, and a due date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the given description and due date/time.
     * The task is initialized as not done.
     *
     * @param description Description of the deadline task.
     * @param by Due date and time in the format {@code yyyy-MM-dd HHmm}.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Constructs a {@code Deadline} task with the given description, due date/time,
     * and completion status.
     *
     * @param description Description of the deadline task.
     * @param by Due date and time in the format {@code yyyy-MM-dd HHmm}.
     * @param isDone Completion status of the task ({@code true} if done, otherwise {@code false}).
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, description, status, and due date/time.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma")) + ")";
    }

    /**
     * Returns a string representation of the deadline task formatted for file storage.
     *
     * @return File-formatted string representation of the deadline task.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + super.getStatusIcon() + " | " + description + " | " + by.format(fmt);
    }

}
