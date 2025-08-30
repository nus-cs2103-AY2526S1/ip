package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * Inherits from Task class and adds a deadline date/time.
 */
public class Deadline extends Task {
    /** The deadline by which the task should be completed */
    private LocalDate by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task
     * @param by The deadline by which the task should be completed (LocalDate)
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new Deadline task with the given description and deadline string.
     * Parses the date string in yyyy-mm-dd format.
     *
     * @param description The description of the deadline task
     * @param byString The deadline as a string (yyyy-mm-dd format)
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = LocalDate.parse(byString);
    }

    /**
     * Gets the deadline date.
     * @return The deadline date
     */
    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
