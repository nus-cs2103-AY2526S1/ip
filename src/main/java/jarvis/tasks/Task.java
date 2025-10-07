package jarvis.tasks;

/**
 * Represents an abstract task in the assistant.
 * <p>
 * A {@code Task} has a description and a completion status
 * (done or not done). Subclasses such as {@link DeadlinesTask},
 * {@link EventsTask}, and {@link ToDoTask} define additional
 * attributes and behavior.
 * </p>
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;
    /** Indicates whether the task is completed. */
    protected boolean isDone;

    /**
     * Constructs a {@code Task}.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing completion.
     * <p>
     * The format is {@code "[X]"} if done, otherwise {@code "[ ]"}.
     * </p>
     *
     * @return A string representing the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the type icon of the task.
     * <p>
     * Subclasses must implement this method to provide a unique
     * symbol (e.g., {@code "[D]"} for deadlines, {@code "[E]"} for events).
     * </p>
     *
     * @return A string representing the type of the task.
     */
    protected abstract String getTypeIcon();

    /**
     * Returns the string representation of the task for storage.
     * <p>
     * Subclasses must implement this method to define how tasks
     * are serialized (e.g., pipe-delimited format).
     * </p>
     *
     * @return A string representation of the task suitable for storage.
     */
    public abstract String toStorageString();

    /**
     * Returns the string representation of the task for display.
     * <p>
     * The default format includes the status icon and description.
     * Example: {@code [X] Finish homework}.
     * </p>
     *
     * @return A string representation of the task for user display.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Checks whether the task is marked as done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
