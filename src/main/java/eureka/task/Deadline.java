package eureka.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task with a specific due date and time.
 * A deadline is a task that must be completed by a certain date and time.
 */
public class Deadline extends Task {
    private LocalDateTime due;

    /**
     * Creates a new Deadline with the specified due date, task name, and completion status.
     *
     * @param due the date and time when the task is due
     * @param taskName the name/description of the deadline task
     * @param isDone whether the deadline task is marked as completed
     */
    public Deadline(LocalDateTime due, String taskName, boolean isDone) {
        super(taskName, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        String dueStr = (due != null) ? dateTimeFormatter(due) : "N/A";
        return "[D] " + super.toString() + " (by: " + dueStr + ")";
    }

    @Override
    public String serialise() {
        return "D | " + super.serialise() + " | " + this.due;
    }
}
