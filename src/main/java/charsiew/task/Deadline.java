package charsiew.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline has a description (name) and a due date.
 */
public class Deadline extends Task {
    /** The due date of the deadline task. */
    protected LocalDate by;

    /**
     * Constructs a Deadline task with a name and a due date.
     *
     * @param name The description of the task.
     * @param by The due date of the task (expects format yyyy-MM-dd).
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns the due date of this deadline task.
     *
     * @return The due date as a LocalDate object.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task,
     * including its type, completion status, name, and formatted due date.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
