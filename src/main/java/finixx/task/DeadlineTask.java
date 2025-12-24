package finixx.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description, completion status, optional notes, and a deadline.
 * Extends the abstract Task class by adding a deadline field.
 * Stores the description, completion status, notes, and deadline of the deadline task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs a DeadlineTask with the specified description, completion status, notes, and deadline.
     *
     * @param description Description of the task
     * @param isDone Completion status of the task
     * @param note Note associated with the task
     * @param deadline Deadline of the task in "yyyy-MM-dd" format
     */
    public DeadlineTask(String description, boolean isDone, String note, String deadline) {
        super(description, isDone, note);
        assert deadline != null && !deadline.isEmpty() : "Deadline cannot be null or empty";
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return The deadline as a string
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a string representation of this DeadlineTask for display.
     *
     * @return String representation of the DeadlineTask
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline() + ")"
                + (getNote().isEmpty() ? "" : " <" + getNote() + ">");
    }

    private String formatDeadline() {
        LocalDate parsedDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts this DeadlineTask into a string suitable for saving to a file.
     *
     * @return String representing the DeadlineTask for storage
     */
    @Override
    public String toSaveFormatString() {
        return "D | " + (isDone() ? "1" : "0") + " | "
                + getDescription() + " | " + deadline + " | "
                + getNote();
    }
}
