package floydai.task;

/**
 * Represents a generic task in the task management system.
 * <p>
 * Each task has a description, a completion status, and a type
 * (represented by {@link TaskType}). This is an abstract base
 * class that other specific task types (e.g., deadline, event,
 * todo) should extend.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** Indicates whether the task is completed. */
    protected boolean isDone;

    /** The type of the task (see {@link TaskType}). */
    protected TaskType type;

    /**
     * Constructs a new {@code Task}.
     *
     * @param description The description of the task.
     * @param type        The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon representing the completion state.
     *
     * @return {@code "X"} if the task is done, otherwise a space {@code " "}.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, otherwise {@code false}.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return The task type.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns a string representation of the task, including its type,
     * completion status, and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + type.getIcon() + "][" + getStatusIcon() + "] " + description;
    }
}
