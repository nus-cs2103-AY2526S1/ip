package hermione.tasks;

import java.time.LocalDateTime;

import hermione.utils.DateUtils;

/**
 * Represents a Deadline task in the Hermione application.
 */
public class Deadline extends Task {

    private final LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description, completion status,
     * and deadline date and time.
     *
     * @param description The description of the deadline task.
     * @param isCompleted Whether the task is completed.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Gets the deadline date and time.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getCode() + "]"
                + super.toString()
                + " (by: %s)".formatted(DateUtils.formatDate(this.by));
    }
}
