package som.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import som.SomException;

/**
 * Represents a deadline task with a due date/time.
 * <p>A deadline task includes a description and a deadline. The deadline is stored as a object for
 * accurate date-time comparison and formatting.</p>
 *
 * @author Darien Tan
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    private LocalDateTime by;

    /**
     * Constructs a new Deadline task.
     *
     * @param description the task description.
     * @param strBy the deadline date/time.
     */
    public Deadline(String description, String strBy) throws SomException {
        super(description);
        try {
            this.by = LocalDateTime.parse(strBy, INPUT_FORMAT);
            // Check if the deadline is before or equal to now
            if (this.by.isBefore(LocalDateTime.now()) || this.by.isEqual(LocalDateTime.now())) {
                throw new SomException("Deadline must be after the current time.");
            }
        } catch (DateTimeParseException e) {
            throw new SomException("Invalid date format! Use: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)");
        }
    }

    /**
     * Returns the start date and time of the Deadline.
     *
     * @return the LocalDateTime representing when the task is due.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Encodes a task into a line like: D | 1 | read book | 16 Aug.
     *
     * @return the formatted representation of the task to be saved.
     */
    @Override
    public String encode() {
        return ("D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by.format(INPUT_FORMAT));
    }

    @Override
    protected LocalDateTime getSortTime() {
        return this.by;
    }

    /**
     * Returns string representation of the Deadline task.
     *
     * @return the formatted task string.
     */
    @Override
    public String toString() {
        return " [D] " + super.toString() + " (by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }
}
