package dobby.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected LocalDateTime by;

    /**
     * Represents a task that has a specific deadline.
     * A {@code Deadline} stores a description of the task and the date/time
     * by which it must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert by != null : "Deadline date 'by' cannot be null";
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the deadline task.
     * Format: {@code [D] [status] description (by: MMM dd yyyy, h:mma)}.
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D] " + super.toString() + "(by: " + by.format(outputFormatter) + ")";
    }
}
