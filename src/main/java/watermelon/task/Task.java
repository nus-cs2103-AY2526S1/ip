package watermelon.task;

/**
 * The base class for all types of tasks in the Watermelon chatbot application.
 *
 * <p> A task has a description and a completion status (done or not done).
 * This class provides common functionalities that all task types share,
 * such as marking tasks as done or undone, and displaying task status.</p>
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description the description of the task.
     * @param isDone completion status of task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     *
     * @return "X" if the task is done, " " if the task is not done.
     */
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
        return this.description;
    }

    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Converts this task to a file format string for storage.
     *
     * <p>This method should be overridden by subclasses
     * to provide their specific file format representation.
     * The base implementation returns an empty string.</p>
     *
     * @return an empty string
     */
    public String toFileFormat() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

