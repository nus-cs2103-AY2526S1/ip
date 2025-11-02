package olaf.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import olaf.TaskType;

/**
 * Represents a Deadline task which has a description and a due date/time.
 * This class extends Task and adds deadline-specific information
 */
public class Deadline extends Task {

    private LocalDateTime deadline;
    private static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("d MMM yyyy h:mma");

    /**
     * Constructs a Deadline task from the description and deadline string.
     * Parses the deadline string to LocalDateTime
     *
     * @param description Description of the deadline task.
     * @param deadline Deadline string in the format "d/M/yyyy HHmm"
     */
    public Deadline(String description, String deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
        assert this.description != null : "Description should not be null";
        assert this.type != null : "Task type should not be null";
        assert this.deadline != null : "Deadline cannot be null";

    }

    /**
     * Constructs a Deadline task from the description and LocalDateTime deadline.
     *
     * @param description Description of the deadline task
     * @param deadline Deadline as a LocalDateTime object
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
        assert this.description != null : "Description should not be null";
        assert this.type != null : "Task type should not be null";
        assert this.deadline != null : "Deadline cannot be null";
    }

    @Override
    public String toString() {
        return "[D]" +
                "[" + (isDone ? "X" : " ") + "] " +
                this.description + " (by: " + this.deadline.format(OUTPUT_FORMATTER) + ")";
    }

    public String getDeadline() {
        return this.deadline.format(INPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return deadline.equals(other.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deadline);
    }
}
