package dwight.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline. A {@code Deadline} includes a description and a due
 * date.
 */
public class Deadline extends Task<Deadline> {

    /** The due date of the deadline task. */
    private LocalDate deadline;

    /**
     * Creates a new {@code Deadline} task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param deadline The due date of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        assert deadline != null : "Deadline due date cannot be null.";
        this.deadline = deadline;
    }

    @Override
    public String getUniqueKey() {
        return "D:" + super.getUniqueKey() + this.deadline;
    }

    /**
     * Returns a string representation of this deadline task for display purposes. The string
     * includes the task type identifier {@code [D]} and the formatted due date.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        String dateStr = this.deadline.format(formatter);
        return "[D]" + super.toString() + " (by: " + dateStr + ")";
    }

    /**
     * Returns a serialized representation of this deadline task suitable for saving to storage. The
     * format begins with the task type identifier {@code D}, followed by the description,
     * completion status, and due date.
     *
     * @return The serialized string of the deadline task.
     */
    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String dateStr = this.deadline.format(formatter);
        return "D | " + super.serialize() + " | " + dateStr;
    }
}
