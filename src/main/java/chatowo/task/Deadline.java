package chatowo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * Extends Task with deadline date functionality.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a new deadline task.
     *
     * @param name Task description
     * @param deadline Deadline date
     * @throws DateTimeParseException if date format is invalid
     */
    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Converts deadline to storage format string.
     *
     * @return String in format "D | isDone | description | date"
     */
    @Override
    public String toDataString() {
        return "D" + super.toDataString() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
