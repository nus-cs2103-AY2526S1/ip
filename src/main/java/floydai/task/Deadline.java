package floydai.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import floydai.FloydException;

/**
 * Represents a deadline task with a specific due date.
 * <p>
 * Inherits from {@link Task} and adds a {@code by} date stored as {@link LocalDate}.
 */
public class Deadline extends Task {
    /** Formatter for parsing input dates (yyyy-MM-dd). */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Formatter for displaying the due date in a user-friendly format (MMM d yyyy). */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /** Due date of the deadline task. */
    private final LocalDate by;

    /**
     * Constructs a {@code Deadline} task with a description and due date.
     *
     * @param description Description of the deadline task.
     * @param by Due date as a string in yyyy-MM-dd format.
     * @throws FloydException If the date string is not in the expected format.
     */
    public Deadline(String description, String by) throws FloydException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new FloydException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-12-02).");
        }
    }

    /**
     * Returns the due date of this deadline.
     *
     * @return {@link LocalDate} representing the due date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     * Includes the task type, status, description, and formatted due date.
     *
     * @return A string representing this deadline task.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
