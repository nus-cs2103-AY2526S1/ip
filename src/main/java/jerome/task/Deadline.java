package jerome.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the system.
 * A <code>Deadline</code>  object holds details like description, deadline date and whether it is completed.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructs a new <code>Deadline</code> with description, deadline date and the status for whether it is done set to false.
     *
     * @param description Description of the deadline.
     * @param by The date of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadline = LocalDate.parse(by);
    }

    /**
     * Constructs a new <code>Deadline</code> with description, deadline date and the status for whether it is done.
     *
     * @param description Description of the deadline.
     * @param by The date of the deadline.
     * @param isDone Whether this deadline task is done.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.deadline = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (deadline: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Provides string representation to save in .txt file
     * @return corresponding string representation for a Deadline task
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.deadline;
    }

    @Override
    public void adjustDate(String by) {
        this.deadline = LocalDate.parse(by);
    }
}
