package yang.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} has a description and a due date.
 */
public final class Deadline extends Task {

    private final LocalDate by;
    private final DateTimeFormatter OUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a {@code Deadline} that is initially not done.
     *
     * @param description text describing the task
     * @param by          due date of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description, false);
        this.by = by;
    }

    /**
     * Constructs a {@code Deadline} with an explicit done state.
     *
     * @param description text describing the task
     * @param by          due date of the task
     * @param isDone      whether the task is already completed
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public boolean sameIdentity(Task other) {
        if (!(other instanceof Deadline d)) {
            return false;
        }
        return this.description.trim().equalsIgnoreCase(d.description.trim())
                && this.by.equals(d.getBy());
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "X" : " ") + "] " + description
                + " (by: " + by.format(OUT_FORMAT) + ")";
    }

    @Override
    public String toStorage() {
        return "D | " + (isDone ? "1" : "0") + " | "
                + description + " | " + by;
    }
}
