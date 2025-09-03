package evansbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event task with a description and a due date.
 * The due date can be either a valid LocalDate or a raw string if parsing fails.
 */
public class Event extends Task {
    protected LocalDate fromDate;
    protected String fromRaw;
    protected LocalDate toDate;
    protected String toRaw;

    /**
     * Constructs a Event task with the specified description, from and to date string.
     * Attempts to parse the from and to date as LocalDate; if parsing fails, stores the raw string.
     *
     * @param description Description of the task.
     * @param from Begin date as a string.
     * @param to end date as a string.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.fromDate = LocalDate.parse(from);
            this.fromRaw = from;
            this.toDate = LocalDate.parse(to);
            this.toRaw = to;
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.fromRaw = from;
            this.toDate = null;
            this.toRaw = to;
        }
    }

    /**
     * Constructs a Event task with the specified description, completion status, from and to date string.
     * Attempts to parse the from and to date as LocalDate; if parsing fails, stores the raw string.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is marked as done.
     * @param from Begin date as a string.
     * @param to end date as a string.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        try {
            this.fromDate = LocalDate.parse(from);
            this.fromRaw = from;
            this.toDate = LocalDate.parse(to);
            this.toRaw = to;
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.fromRaw = from;
            this.toDate = null;
            this.toRaw = to;
        }
    }

    /**
     * Returns the parsed LocalDate of the task's From date.
     *
     * @return LocalDate representation of the From date, or null if parsing failed.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the parsed LocalDate of the task's due date.
     *
     * @return LocalDate representation of the due date, or null if parsing failed.
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Returns the parsed LocalDate of the task's From date.
     *
     * @return String representation of the From date.
     */
    public String getFromRaw() {
        return fromRaw;
    }

    /**
     * Returns the parsed LocalDate of the task's due date.
     *
     * @return String representation of the due date.
     */
    public String getToRaw() {
        return toRaw;
    }

    /**
     * Returns the string representation of the Event task, including its description,
     * completion status, and formatted from and to date.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        String from;
        String to;

        if (fromDate != null && toDate != null) {
            from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            from = fromRaw;
            to = toRaw;
        }
        return "[E]" + super.toString() + " (From: " + from + " to: " + to + ")";
    }
}
