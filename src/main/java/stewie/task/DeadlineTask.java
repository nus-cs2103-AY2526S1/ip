package stewie.task;

import java.time.LocalDateTime;

import stewie.util.Helper;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDateTime deadline;

    /**
     * Creates a new DeadlineTask with the specified description and deadline.
     *
     * @param description The task description.
     * @param deadline The deadline for the task.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileFormat() {
        String formattedDeadline = Helper.dateTimeToFileFormat(deadline);
        return "D | " + super.toFileFormat() + " | " + formattedDeadline;
    }

    @Override
    public String getDescription() {
        String formattedDeadline = Helper.dateTimeToString(deadline);
        return "[D]" + super.getDescription() + " (by: " + formattedDeadline + ")";
    }
}
