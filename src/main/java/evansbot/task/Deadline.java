package evansbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a description and a due date.
 * The due date can be either a valid LocalDate or a raw string if parsing fails.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected String byRaw;

    /**
     * Constructs a Deadline task with the specified description and due date string.
     * Attempts to parse the due date as LocalDate; if parsing fails, stores the raw string.
     *
     * @param description Description of the task.
     * @param by Due date as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.byDate = LocalDate.parse(by);
            this.byRaw = by;
        } catch (DateTimeParseException e) {
            this.byDate = null;
            this.byRaw = by;
        }
    }

    /**
     * Constructs a Deadline task with the specified description, completion status, and due date string.
     * Attempts to parse the due date as LocalDate; if parsing fails, stores the raw string.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is marked as done.
     * @param by Due date as a string.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.byDate = LocalDate.parse(by);
            this.byRaw = by;
        } catch (DateTimeParseException e) {
            this.byDate = null;
            this.byRaw = by;
        }
    }

    /**
     * Returns the parsed LocalDate of the task's due date.
     *
     * @return LocalDate representation of the due date, or null if parsing failed.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * Returns the string representation of the Deadline task, including its description,
     * completion status, and formatted due date.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        String date;
        if (byDate != null) {
            date = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            date = byRaw;
        }
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
