package jerome.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * Extends the abstract class Task to add a specific deadline for a task.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime by;
    /**
     * Constructs a {@code Deadline} with the given description and due date.
     *
     * @param description Task description.
     * @param by Deadline in format "d/M/yyyy HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormat);
    }

    /**
     * Returns the raw format of the deadline (d/M/yyyy HHmm)
     */
    public String getByRaw() {
        return by.format(inputFormat);
    }

    /**
     * Returns the deadline as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatus() + "] " + description + "(by: "
                + by.format(displayFormat) + ")";
    }
}
