package phuc.model;

import phuc.parser.DateTimeProcess;
import java.time.LocalDateTime;

/**
 * Represents a deadline task with a specific due date and time.
 * Extends the base Task class with deadline functionality.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a new DeadlineTask with the specified description and deadline.
     *
     * @param description the task description
     * @param deadline the deadline date and time
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a DeadlineTask with completion status.
     *
     * @param description the task description
     * @param deadline the deadline date and time
     * @param isDone whether the task is completed
     */
    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Converts the task to a string format suitable for file storage.
     *
     * @return a string representation for file storage
     */
    @Override
    public String writeToFile() {
        return "D | " + isDone + " | " + description + " | " + DateTimeProcess.formatForFile(deadline);
    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return a formatted string showing task details and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeProcess.formatForDisplay(deadline) + ")";
    }
}
