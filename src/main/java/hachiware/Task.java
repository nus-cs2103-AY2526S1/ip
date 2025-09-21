package hachiware;

/**
 * Represents a generic task in a tasklisk in Hachiware.
 */
public class Task {

    /** Description of task. */
    protected String description;
    /** Completion status of the task. */
    protected boolean isDone;
    protected Priority priority;

    /**
     * Creates a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.UNTAGGED;
    }


    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getPriorityString() {
        return priority == null ? "Untagged" : priority.name();
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return task string including status icon and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " (Priority: " + priority + ")";

    }
}
