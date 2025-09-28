package alpaca.task;

/**
 * Represents a generic task with a description and completion status.
 * Serves as a base class for more specific task types such as {@link Deadline}, {@link Event}, and {@link Todo}.
 */
public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** Whether the task is marked as done. */
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task; must not be null or empty.
     * @throws IllegalArgumentException if description is null or blank.
     */
    public Task(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be null or empty");
        }
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks the task as not done. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing completion.
     *
     * @return "X" if done, " " if not done.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a string suitable for saving to a file.
     * Subclasses should override this to provide custom save formats.
     *
     * @return A string representing the task for storage.
     */
    public String toSaveFormat() {
        return "";
    }

    /**
     * Returns a user-friendly string representation of the task.
     *
     * @return The task as a formatted string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
}
