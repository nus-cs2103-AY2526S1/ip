package nerpbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date in YYYY-MM-DD format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by); // expects date in YYYY-MM-DD format
    }

    /**
     * Converts the deadline task to a string format suitable for saving to a file.
     *
     * @return The string representation of the deadline task for saving.
     */
    @Override
    public String saveFormat() {
        return "D | " + super.saveFormat() + " | " + by.toString();
    }

    /**
     * Returns the string representation of the deadline task for display.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
