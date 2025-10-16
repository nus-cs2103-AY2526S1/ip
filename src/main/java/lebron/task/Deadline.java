package lebron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lebron.common.Constants;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     *  Constructor for Deadline class
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        assert by != null : "Deadline date should not be null";
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return Constants.TYPE_PREFIX_D + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern(Constants.DISPLAY_DATE_PATTERN)) + ")";
    }
}
