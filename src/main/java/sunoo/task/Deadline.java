package sunoo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * The task should be completed by a specific date and time.
 */
public class Deadline extends Task {

    /** The date and time by which this task must be completed. */
    private final LocalDateTime by;

    /**
     * Creates a new deadline task.
     *
     * @param isDone Whether the task is completed.
     * @param description Description of the task.
     * @param by The date and time the task is due.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Compares this Deadline with another object.
     *
     * @param o Object to compare with this Deadline.
     * @return true If both Deadline tasks have the same completion state, description, and deadline; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deadline other) {
            return (this.isDone == other.isDone && this.by.equals(other.by)
                    && this.description.equals(other.description));
        }
        return false;
    }

    /**
     * Returns a string representation of the deadline task
     * Overrides the parent method to prepend the "[D]" icon and show the deadline.
     *
     * @return "[D]" followed by the task's status icon, description, and due date/time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy h:mma")) + ")";
    }

    /**
     * Returns a string representation of the deadline task for saving to a text file.
     * Overrides the parent method to prepend "D" and include the deadline.
     *
     * @return "D" followed by the base task text representation and the due date/time.
     */
    @Override
    public String getTxtRepresentation() {
        return "D" + super.getTxtRepresentation() + " | " + by;
    }
}
