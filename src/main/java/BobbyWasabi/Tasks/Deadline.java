package BobbyWasabi.Tasks;

import BobbyWasabi.Tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that has a description, completion status, and a due date/time.
 * Extends the {@link Task} class and adds support for deadline-specific behavior.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a new {@code Deadline} task.
     *
     * @param description Description of the task.
     * @param isMarked Whether the task is marked as completed.
     * @param deadline The due date and time of the task.
     */
    public Deadline(String description, boolean isMarked, LocalDateTime deadline) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    /**
     * Returns a formatted string representing the deadline date and time.
     * Format used: "MMM d yyyy HHmm", e.g., "Sep 5 2025 1800".
     *
     * @return A string in the form "by: MMM d yyyy HHmm".
     */
    public String getDeadline() {
        String output = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));

        return String.format("by: %s",
                output);
    }

    /**
     * Returns the task's data in a serialized string format suitable for saving to a file.
     * Format: "D|description|mark|deadline"
     * where {@code mark} is 1 if completed, 0 otherwise,
     * and {@code deadline} is in "d/M/yyyy HHmm" format.
     *
     * @return A string representing the task data for persistent storage.
     */
    @Override
    public String getData() {
        String deadlineOutput = this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.format("D|%s|%s|%s",
                super.getDescription(), super.checked(), deadlineOutput);
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A formatted string that includes task type, completion status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDeadline();
    }

}
