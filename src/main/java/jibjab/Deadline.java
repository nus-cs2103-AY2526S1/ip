package jibjab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task with a specific deadline by which it must be completed.
 * A Deadline extends the Task class and includes a due date and time
 * to indicate when the task should be finished.
 *
 * @author niyniy123
 */
public class Deadline extends Task {

    private static final String INPUT_PATTERN = "[dd/MM/yyyy HH:mm][MMM dd yyyy HH:mm]";
    private static final String DISPLAY_PATTERN = "MMM dd yyyy HH:mm";
    private static final String TYPE_PREFIX = "[D]";
    private static final String BY_PREFIX = " (by: ";
    private static final String CLOSING_PAREN = ")";

    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * The constructor accepts flexible date-time formats for the deadline.
     *
     * @param description the description of the task that needs to be completed
     * @param deadline the deadline date and time as a string. Accepted formats:
     *           "dd/MM/yyyy HH:mm" or "MMM dd yyyy HH:mm"
     */
    public Deadline(String description, String deadline) {
        super(description);
        if (deadline == null || deadline.isBlank()) {
            throw new IllegalArgumentException("Deadline date/time must not be empty");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_PATTERN);
            this.deadline = LocalDateTime.parse(deadline.trim(), formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date/time for deadline. "
                    + "Use 'dd/MM/yyyy HH:mm' or 'MMM dd yyyy HH:mm'");
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * The format includes the deadline type [D], the task status and description
     * and the formatted deadline date and time.
     *
     * @return a formatted string representation of the deadline task including its type,
     *         status, description, and deadline in "MMM dd yyyy HH:mm" format
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Deadline d = (Deadline) o;
        return Objects.equals(this.deadline, d.deadline);
    }
}
