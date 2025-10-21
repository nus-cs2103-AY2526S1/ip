package task;

import java.time.LocalDate;

/**
 * Deadline class to represent deadline tasks
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor of deadline
     * @param description Description of task
     * @param by LocalDate object representing deadline time
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.dateToString(this.by) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.by;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline t) {
            return super.equals(o) && t.by.equals(this.by);
        }
        return false;
    }
}
