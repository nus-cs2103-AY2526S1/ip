package bytebuddy.task;

/**
 * Represents a generic task in ByteBuddy.
 * <p>
 * A task stores a description, its completion status,
 * and its type ({@link TaskType}).
 * Subclasses such as {@link Todo}, {@link Deadline}, and {@link Event}
 * extend this class to add additional fields.
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Creates a new task with an explicit completion status.
     *
     * @param description The description of the task
     * @param taskType    The type of the task
     * @param isDone      Whether the task is already marked as done
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public Task(String description, TaskType taskType) {
        this(description, taskType, false);
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return "1" if the task is done, otherwise "0"
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
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
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task,
     * formatted for saving to disk.
     *
     * @return A string in the format "{type} | {status} | {description}"
     */
    public String toString() {
        return taskType.getSymbol() + " | " + getStatusIcon() + " | " + description;
    }
}