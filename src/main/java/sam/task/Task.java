package sam.task;

/**
 * Represents a task in the task management system.
 * This is the base class for all types of tasks (Todo, Deadline, Event).
 * Each task has a description, priority level, and can be marked as done or not done.
 */
public class Task {
    protected final String description;
    private boolean isDone;
    private Priority priority;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done and has medium priority.
     *
     * @param description The description of the task
     */
    public Task(final String description) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.isDone = false; // default not done
        this.priority = Priority.MEDIUM; // default medium priority
    }

    /**
     * Constructs a new Task with the given description and priority.
     * The task is initially marked as not done.
     *
     * @param description The description of the task
     * @param priority The priority level of the task
     */
    public Task(final String description, final Priority priority) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        assert priority != null : "Priority cannot be null";
        this.description = description;
        this.isDone = false; // default not done
        this.priority = priority;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The new priority level
     */
    public void setPriority(Priority priority) {
        assert priority != null : "Priority cannot be null";
        this.priority = priority;
    }

    /**
     * Returns the priority of the task.
     *
     * @return The priority level of the task
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task type.
     * Subclasses should override this to provide their specific type indicator.
     *
     * @return A string representing the task type (empty string for base Task
     *         class)
     */
    protected String kind() {
        return "";
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return A string in the format "[X]" for done tasks or "[ ]" for undone tasks
     */
    protected String status() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task type, status, priority, and description
     */
    @Override
    public String toString() {
        return kind() + status() + " [" + priority.getDisplayName() + "] " + description;
    }
}
