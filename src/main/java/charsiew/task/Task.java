package charsiew.task;

/**
 * Represents a generic task with a name and completion status.
 */
public class Task {
    /** The description of the task. */
    protected String name;
    /** Indicates whether the task is completed. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified name.
     * The task is initially not done.
     *
     * @param name The description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
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
     * Returns the status icon of the task.
     * "X" if done, " " (space) if not done.
     *
     * @return The status icon as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the name/description of the task.
     *
     * @return The task's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the task,
     * including its completion status and name.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}
