package junny.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import junny.TaskTypes;

/**
 * Represents a task with a deadline.
 * A {@code Deadline} stores a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} with the given description and deadline date.
     *
     * @param description The description of the task.
     * @param by The due date of the deadline task in {@code yyyy-MM-dd} format.
     */
    public Deadline(String description, String by) {
        super(description, TaskTypes.DEADLINE);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return The formatted string showing type, status, description, and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }

    /**
     * Returns the due date of the deadline.
     *
     * @return The {@code LocalDate} representing the due date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task for file storage.
     *
     * @return A string in the format {@code D | status | description | by}.
     */
    @Override
    public String toFileString() {
        int status;
        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }
        return "D | " + status + " | " + this.description + " | " + this.by;
    }
}
