package som.task;

import java.time.LocalDateTime;

/**
 * Represents a generic Task in the task list.
 * <p>This abstract class serves as the base for all specific task types, such as
 * Todo, Deadline and Event. It contains common properties such as description
 * and completion status. Subclasses must implement the toString() and encode()
 * methods to define their string representation and format. </p>
 *
 * @author Darien Tan
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description the task description, must not be null.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected LocalDateTime getSortTime() {
        return null; // Default: no time → treated as latest
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon: "X" if done, " " otherwise.
     *
     * @return "X" if the task is done, " " if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "  "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Encodes a task the specified format for saving.
     *
     * @return the formatted representation of the task to be saved.
     */
    public abstract String encode();

    @Override
    public int compareTo(Task other) {
        LocalDateTime thisTime = this.getSortTime();
        LocalDateTime otherTime = other.getSortTime();

        if (thisTime == null && otherTime == null) {
            return 0;
        }
        if (thisTime == null) {
            return 1; // This comes after
        }
        if (otherTime == null) {
            return -1; // This comes before
        }
        return thisTime.compareTo(otherTime);
    }

    /**
     * Returns string representation of the task.
     *
     * @return formatted task string.
     */
    @Override
    public String toString() {
        return " [" + getStatusIcon() + "] " + description;
    }
}
