package itachi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A {@code Deadline} has a description inherited from {@link Task}
 * and a due date represented by a {@link LocalDate}.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} task with the given description and deadline date.
     * @param description the description of the task
     * @param by the due date of the task in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != null : "Description can't be null";
        assert by != null : "Deadline date string can't be null";
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + this.by;
    }

    public LocalDate getBy() {
        return this.by;
    }
}

