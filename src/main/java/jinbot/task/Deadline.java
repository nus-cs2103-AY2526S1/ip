package jinbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this deadline task
     * in the format used for saving to disk.
     *
     * @return A save format string for this deadline task.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (getIsDone() ? "1" : "0")
            + " | " + getTaskName() + " | " + by.toString() + " | " + getPriority();
    }

    /**
     * Returns the string representation of this deadline task for display.
     *
     * @return The string containing the task type, status, description, and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")" + " [" + getPriority() + "]";
    }
}
