package nyanchan.tasks;

import nyanchan.exceptions.IncorrectFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * Represents a task with a specific deadline date.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate deadline;

    /** Formatter for parsing input dates (dd/MM/yyyy). */
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /** Formatter for displaying dates in a readable form. */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new Deadline task.
     *
     * @param description task description
     * @param by date string in dd/MM/yyyy format
     * @throws IncorrectFormatException if the date format is invalid
     */
    public Deadline(String description, String by) throws IncorrectFormatException {
        super(description);
        this.convertToDate(by);
    }

    /**
     * Returns the formatted deadline date string.
     *
     * @return formatted date string
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the deadline as a LocalDate.
     *
     * @return deadline date
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Converts a date string to LocalDate and stores formatted output.
     *
     * @param by date string in dd/MM/yyyy format
     * @throws IncorrectFormatException if the date cannot be parsed
     */
    public void convertToDate(String by) throws IncorrectFormatException {
        try {
            this.deadline = LocalDate.parse(by, INPUT_FORMAT);
            this.by = this.deadline.format(OUTPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new IncorrectFormatException("HISS, invalid date format! Use dd/MM/yyyy!");
        }
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return formatted task string
     */
    @Override
    public String toString() {
        String formattedDate = this.deadline.format(OUTPUT_FORMAT);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
