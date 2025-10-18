package charli.task;

import charli.exception.CharliException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents a deadline task with a specific due date and time.
 * Deadlines are time-sensitive tasks that must be completed by a certain point.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    //define format for input and display
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    /**
     * Constructs a new Deadline task from string input.
     *
     * @param description the description of the deadline task
     * @param by the due date string in "d/M/yyyy HHmm" format
     * @throws DateTimeParseException if the date string format is invalid
     */
    public Deadline(String description, String by) throws DateTimeParseException, CharliException {
        super(description);
        assert by != null : "Input of Deadline date and time must not be null";
        LocalDateTime deadline = LocalDateTime.parse(by, INPUT_FORMATTER);
        this.by = deadline;
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new CharliException("Deadline must be after current time...");
        }

    }

    //Constructor for internal use - parse previous tasks in storage file
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert by != null : "Stored Deadline date and time must not be null";
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task.
     * Format: [D][Status] Description (by: Formatted Due Date)
     *
     * @return the formatted string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMATTER) + ")";
    }

}
