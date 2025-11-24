package burgerburglar;

/**
 * Represents a generic task in BurgerBurglar.
 * <p>
 * A task has a description and a completion status (done or not done).
 * This abstract class should be extended by concrete task types such as
 * {@link Deadline}, {@link Event}, and {@link Todo}, which must implement
 * {@link #serialize()} to convert the task to a storable string format.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * The task is initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        assert description != null && !description.isBlank() : "Task description cannot be null or blank";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone      whether the task is initially done
     */
    public Task(String description, boolean isDone) {
        assert description != null && !description.isBlank() : "Task description cannot be null or blank";
        this.description = description;
        this.isDone = isDone;
    }

    /** Returns the task description. */
    public String getDescription() {
        return description;
    }

    /** Returns whether the task is completed. */
    public boolean isDone() {
        return isDone;
    }

    /** Marks the task as completed. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks the task as not completed. */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representing the completion status.
     *
     * @return "[X]" if done, "[ ]" if not done
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a string representing the task type.
     * Override in subclasses to provide specific type icons.
     *
     * @return the type icon
     */
    public String getTypeIcon() {
        return "[ ]"; // default placeholder
    }

    /** Returns a human-readable string representation of the task. */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description;
    }

    /** Converts the task to a string suitable for storage in a file. */
    public abstract String serialize();
}
