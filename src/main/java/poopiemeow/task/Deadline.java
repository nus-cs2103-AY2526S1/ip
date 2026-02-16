package poopiemeow.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import poopiemeow.exception.EmptyDescriptionException;

/**
 * Represents a deadline task in the PoopieMeow application.
 * A deadline is a task that must be completed by a specific date and time.
 * It extends the base Task class and adds deadline functionality.
 *
 * @author tch1001
 * @version 1.0
 */
public class Deadline extends Task {
    /** The deadline date and time when the task must be completed */
    private LocalDateTime deadline;

    /**
     * Constructs a new Deadline task with the specified description and deadline.
     * The description must not be empty or contain only whitespace.
     * The deadline must be in the format "yyyy-MM-dd HHmm".
     *
     * @param description a description of what the deadline involves
     * @param deadlineStr the deadline string in "yyyy-MM-dd HHmm" format
     * @throws EmptyDescriptionException if the description is empty or contains only whitespace
     * @throws DateTimeParseException if the deadline string format is invalid
     */
    public Deadline(String description, String deadlineStr) throws EmptyDescriptionException, DateTimeParseException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
        }
        this.deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the deadline date and time for this task.
     *
     * @return the LocalDateTime representing when the task must be completed
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Converts the deadline task to its file storage format.
     * The format follows the pattern: D|status|description|deadline
     *
     * @return a string representation of the deadline suitable for file storage
     */
    @Override
    public String toFileString() {
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the deadline task.
     * The format includes the task type indicator [D], completion status,
     * description, and formatted deadline.
     *
     * @return a string showing the deadline's status, description, and due date
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " +
               deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
