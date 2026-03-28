package luna.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline with the given description
     *
     * @param description Description of task
     * @param isDone Status of task, usually initialised to false
     * @param by Deadline of task
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.by = LocalDateTime.parse(by, inputFormat);
    }


    /**
     * Returns a string representation of the task for display.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(outputFormat) + ")"
                + super.tagToString();
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D" + super.toFileString() + " | " + this.by.format(outputFormat) + super.tagToString();
    }
}

