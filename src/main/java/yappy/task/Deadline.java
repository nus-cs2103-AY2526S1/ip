package yappy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a <code>Deadline</code> task which is a type of <code>Task</code>.
 * A <code>Deadline</code> requires a description, a completion status and the date of the deadline.
 *
 * <p>This class extends the <code>Task</code> class and overrides methods
 * for file storage and string representation.</p>
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime by;

    /**
     * Constructs a new {@code Deadline} task with the given description,
     * completion status and by date.
     *
     * @param description Textual description of the task.
     * @param isCompleted {@code true} if the task has been completed,
     *                    {@code false} otherwise.
     * @param by Deadline of the task.
     */
    public Deadline(String description, Boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "D|" + this.isCompleted() + "|" + this.getDescription() + "|" + this.by.format(FORMATTER) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Returns the deadline of the event.
     *
     * @return The by date in LocalDateTime
     */
    public LocalDateTime getByDate() {
        return this.by;
    }

    @Override
    public String postpone(LocalDateTime newDeadline) {
        this.by = newDeadline;
        return "Successfully postponed event: \n" + this;
    }
}
