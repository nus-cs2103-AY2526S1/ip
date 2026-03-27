package fish.task;

import java.util.Objects;

/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String getType();

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileString() {
        return String.join(" | ", getType(), isDone ? "1" : "0", description);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return this.getType().equals(other.getType())
                && this.description.equals(other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), description);
    }
}
