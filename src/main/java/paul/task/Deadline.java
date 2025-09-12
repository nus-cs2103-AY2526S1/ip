package paul.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task for Paul.
 * Includes a by date, in MMM dd yyyy format.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline task from the description and by date.
     *
     * @param description The description of the task.
     * @param by The by date in yyyy-mm-dd format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of a deadline in MMM dd yyyy format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    /**
     * Returns the string representation of a deadline for saving into a file.
     *
     * @return The deadline task string for saving.
     */
    @Override
    public String toSaveString() {
        return "D" + super.toSaveString() + " | " + by;
    }

    /**
     * Checks if the given object is equal to a Deadline.
     * They are equal if they have the same description and the same by date.
     *
     * @param obj the reference object with which to compare.
     * @return true if this is equal to the Deadline.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return this.description.equalsIgnoreCase(other.description)
                && this.by.equals(other.by);
    }
}
