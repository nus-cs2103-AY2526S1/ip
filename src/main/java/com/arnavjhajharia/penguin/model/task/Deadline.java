package com.arnavjhajharia.penguin.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A {@link Task} representing a deadline. Stores the task description,
 * a due date (parsed from ISO format {@code yyyy-MM-dd}), and provides
 * formatted output for storage and display.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Validate and parse the provided deadline string into a {@link LocalDate}.</li>
 *   <li>Render a storage-friendly line via {@link #toStorageLine()}.</li>
 *   <li>Render a user-friendly description with a nicely formatted date via {@link #toString()}.</li>
 * </ul>
 *
 * @since 1.0
 */
public final class Deadline extends Task {

    /**
     * Formatter for parsing and formatting ISO date strings (yyyy-MM-dd).
     */
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * The raw deadline string provided at construction. Preserved for error reporting.
     */
    private final String raw;

    /**
     * The parsed deadline date.
     */
    private final LocalDate date;

    /**
     * Creates a new {@code Deadline} task with a description, identifier, and a due date.
     *
     * @param task     description of the task
     * @param id       zero-based identifier within a task list
     * @param deadline the deadline string, expected in ISO format {@code yyyy-MM-dd}
     * @throws IllegalArgumentException if the deadline string cannot be parsed as an ISO local date
     */
    public Deadline(String task, int id, String deadline) {
        super(task, id);
        this.raw = deadline == null ? "" : deadline.trim();
        try {
            this.date = LocalDate.parse(this.raw, ISO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid deadline format. Use yyyy-MM-dd (e.g., 2025-09-02). Got: " + this.raw
            );
        }
    }

    /**
     * Returns a storage-ready line representing this deadline task.
     * <p>
     * Format:
     * <pre>
     * D | &lt;doneFlag&gt; | &lt;description&gt; | &lt;yyyy-MM-dd&gt;
     * </pre>
     *
     * @return formatted line suitable for persistence
     */
    @Override
    public String toStorageLine() {
        return String.format("D | %s | %s | %s", doneFlag(), name, date.format(ISO));
    }

    /**
     * Returns a user-facing string representation of this deadline task,
     * showing the task description and deadline in a pretty format.
     * <p>
     * Example:
     * <pre>
     * [D] read book (by: Sep 3, 2025)
     * </pre>
     *
     * @return formatted user-facing representation
     */
    @Override
    public String toString() {
        String pretty = date.format(DateTimeFormatter.ofPattern("MMM d, uuuu"));
        return String.format("[D] %s (by: %s)", super.toString(), pretty);
    }

    /**
     * Returns the parsed deadline date as a {@link LocalDate}.
     *
     * @return the deadline date
     */
    public LocalDate getDate() {
        return date;
    }
}
