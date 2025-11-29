package cate.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A {@code Deadline} has a description, completion status, and a date/time dueDate which it is due.
 */
public class Deadline extends Task {
    protected LocalDateTime dueDate;

    /**
     * Constructs a {@code Deadline} task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param dueDate The {@link LocalDateTime} representing the deadline of the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Converts this {@code Deadline} task into a machine-readable string
     * for saving to a file. The format is:
     * <pre>
     * D,{doneFlag},{description},{deadline}
     * </pre>
     * where {@code doneFlag} is 1 if completed, 0 if not, and
     * {@code deadline} is formatted as {@code yyyy-MM-dd HHmm}.
     *
     * @return The string representation of this task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("D,%d,%s,%s", isDone ? 1 : 0,
                description, dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }

    /**
     * Returns a human-readable string representation of this {@code Deadline}.
     * The format is:
     * <pre>
     * [D] {taskString} (by: {deadline})
     * </pre>
     * where {@code taskString} comes from {@link Task#toString()} and
     * {@code deadline} is formatted as {@code MMM dd yyyy HHmm}.
     *
     * @return A string representation of this task suitable for display.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
