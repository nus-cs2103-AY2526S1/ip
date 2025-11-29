package cate.task;

/**
 * Represents a generic task with a description and completion status.
 * A {@code Task} can be marked as done or undone, and
 * subclasses must define how the task is saved to a file.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks whether this task is marked as done.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as done by setting its completion status to {@code true}.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done by setting its completion status to {@code false}.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Converts this task into a machine-readable string
     * for saving to a file. Each subclass defines its own format.
     *
     * @return The string representation of this task for file storage.
     */
    public abstract String toFileString();

    public boolean descriptionContains(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns a human-readable string representation of this {@code Task}.
     * The format is:
     * <pre>
     * [X] description   // if the task is done
     * [ ] description   // if the task is not done
     * </pre>
     *
     * @return A string representation of this task suitable for display.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
