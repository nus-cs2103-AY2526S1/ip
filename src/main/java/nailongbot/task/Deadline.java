package nailongbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 *
 * A {@code Deadline} stores both a task description (inherited from
 * {@link Task}) and a due date/time. The due date is parsed from a raw
 * string input into a {@link LocalDateTime} object and can be displayed
 * in both user-friendly and file storage formats.
 *
 */
public class Deadline extends Task {
    private final LocalDateTime byDate;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description the description of the deadline task
     * @param rawDate     the due date as a string in the format d/M/yyyy HHmm,
     * @throws IllegalArgumentException if the date format is invalid
     */
    public Deadline(String description, String rawDate) {
        super(description);
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.byDate = LocalDateTime.parse(rawDate.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
        }
    }

    public LocalDateTime getByDate() {
        return byDate;
    }

    public LocalDateTime getDate() {
        return byDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String newFormatByDate = byDate.format(outputFormatter);
        return "[D]" + super.toString() + " (by: " + newFormatByDate + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + byDate.format(fileFormatter);
    }
}
