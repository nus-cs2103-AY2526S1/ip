package com.neokortex.task;

import com.neokortex.time.Time;

/**
 * Represents a task with a specific deadline.
 * <p>
 * A {@code DeadlineTask} extends the base {@code Task} class with an extra
 * time component that represents the deadline. The deadline is represented
 * by a {@link Time} object.
 * </p>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * D|isMarked|description|timeSerialization
 * </pre>
 * Where {@code timeSerialization} is the result of {@link Time#serialize()}.
 *
 * @see Task
 * @see Time
 */
public class DeadlineTask extends Task {
    private final Time deadline;

    /**
     * Constructs a DeadlineTask with the specified description and deadline.
     * The task is initially unmarked (not completed).
     *
     * @param description the task description.
     * @param deadline the deadline for this task.
     */
    public DeadlineTask(String description, Time deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new DeadlineTask with the specified
     * description, deadline, and completion status.
     *
     * @param description the task description.
     * @param deadline the deadline for this task.
     * @param isMarked the completion status,
     *                 (true for completed, false for incomplete)
     */
    public DeadlineTask(String description, Time deadline, boolean isMarked) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    public Time getDeadline() {
        return this.deadline;
    }

    /**
     * Returns a formatted string representation of this {@code DeadlineTask}.
     * <p>
     * Format: {@code [D][markStatus] description, by deadline}
     * </p>
     *
     * @return a formatted string representation which includes the task
     *         description and deadline.
     */
    @Override
    public String toString() {
        return String.format("%s, by %s", super.toString(), deadline.toString());
    }

    /**
     * Serializes this {@code DeadlineTask} to a string representation for storage.
     * <p>
     * Format: {@code D|isMarked|description|timeSerialization}
     * </p>
     * <p>
     * Where {@code timeSerialization} is the result of {@link Time#serialize()}.
     * </p>
     *
     * @return a string representation of this {@code DeadlineTask} for storage
     * @see Time#serialize()
     * @see Task#baseSerialization()
     */
    @Override
    public String serialize() {
        return String.format("%s|%s", super.baseSerialization(), deadline.serialize());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof DeadlineTask otherTask) {
            return this.description.equals(otherTask.description)
                    && this.isMarked() == otherTask.isMarked()
                    && this.deadline.equals(otherTask.deadline);
        }

        return false;
    }
}
