package com.arnavjhajharia.penguin.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A {@link Task} representing an event with a start and end date/time.
 * Stores the task description, start time, and end time, validating that the
 * end occurs strictly after the start.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Parse start and end timestamps in ISO format ({@code yyyy-MM-dd'T'HH:mm}).</li>
 *   <li>Validate ordering (end must be strictly after start).</li>
 *   <li>Render a storage-ready string for persistence.</li>
 *   <li>Render a user-friendly formatted string for display.</li>
 * </ul>
 *
 * @since 1.0
 */
public final class Event extends Task {

    /**
     * ISO formatter for parsing and formatting event datetimes.
     * <p>
     * Example: {@code 2025-09-02T14:30}.
     */
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    /**
     * Formatter for user-facing display of event datetimes.
     * <p>
     * Example: {@code Sep 2, 2025 2:30 PM}.
     */
    private static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("MMM d, uuuu h:mm a");

    /**
     * Event start timestamp.
     */
    private final LocalDateTime start;

    /**
     * Event end timestamp.
     */
    private final LocalDateTime end;

    /**
     * Constructs a new {@code Event} with description, identifier, and start/end datetimes.
     *
     * @param task     description of the event
     * @param id       zero-based identifier within a task list
     * @param startStr start datetime string, expected in ISO format ({@code yyyy-MM-dd'T'HH:mm})
     * @param endStr   end datetime string, expected in ISO format ({@code yyyy-MM-dd'T'HH:mm})
     * @throws IllegalArgumentException if any input is {@code null}, cannot be parsed, or if end is not strictly after start
     */
    public Event(String task, int id, String startStr, String endStr) {
        super(task, id);
        if (startStr == null || endStr == null) {
            throw new IllegalArgumentException(
                    "Event requires start and end in yyyy-MM-dd'T'HH:mm (e.g., 2025-09-02T14:30)."
            );
        }
        try {
            this.start = LocalDateTime.parse(startStr.trim(), ISO);
            this.end   = LocalDateTime.parse(endStr.trim(), ISO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid event datetime format. Use yyyy-MM-dd'T'HH:mm (e.g., 2025-09-02T14:30). Got: "
                            + startStr + " / " + endStr
            );
        }
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("Event end must be after start.");
        }
    }

    /**
     * Returns a storage-ready representation of this event.
     * <p>
     * Format:
     * <pre>
     * E | &lt;doneFlag&gt; | &lt;description&gt; | &lt;start ISO&gt; | &lt;end ISO&gt;
     * </pre>
     *
     * @return formatted string suitable for persistence
     */
    @Override
    public String toStorageLine() {
        return String.format("E | %s | %s | %s | %s",
                doneFlag(), name, start.format(ISO), end.format(ISO));
    }

    /**
     * Returns a user-facing string representation of this event,
     * including pretty-formatted start and end datetimes.
     * <p>
     * Example:
     * <pre>
     * [E] project meeting (from: Sep 2, 2025 2:30 PM to: Sep 2, 2025 3:30 PM)
     * </pre>
     *
     * @return formatted user-facing representation
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)",
                super.toString(),
                start.format(PRETTY),
                end.format(PRETTY));
    }

    /**
     * Returns the parsed start datetime of this event.
     *
     * @return event start as {@link LocalDateTime}
     */
    public LocalDateTime getStart() { return start; }

    /**
     * Returns the parsed end datetime of this event.
     *
     * @return event end as {@link LocalDateTime}
     */
    public LocalDateTime getEnd()   { return end; }
}
