package cortana.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {

    /**
     * The deadline date and time by which the task should be completed.
     */
    private LocalDateTime by;

    /**
     * Constructs a cortana.task.Deadline task with the specified name and deadline time.
     *
     * @param name The name or description of the deadline task.
     * @param by   The LocalDateTime by which the task is due.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Checks equality between this Deadline and another object.
     * Two Deadline objects are considered equal if they have passed the superclass equality check,
     * and their 'by' fields are equal.
     * @param obj
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return by.equals(deadline.by);
    }

    /**
     * Generates a hash code for the Deadline object, combining the superclass hash code
     * with the hash codes of the 'by' fields.
     * @return the computed hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), by);
    }

    /**
     * Returns a string representation of the cortana.task.Deadline task, including the task type,
     * completion status, name, and formatted deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        // Format 'by' into a string e.g., 05 SEP 25 0430
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        String text = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + text + ")";
    }
}
