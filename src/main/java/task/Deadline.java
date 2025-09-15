package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class managing the internals of a Deadline object
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline object
     * @param description task name
     * @param by date of deadline for task
     */
    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return this.by;
    }

    public void setBy(String newBy) {
        this.by = LocalDate.parse(newBy);
    }
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
