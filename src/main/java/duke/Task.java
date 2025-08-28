/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Creates a new Task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The completion status to set
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Gets the icon representing the task's completion status.
     *
     * @return "X" if done, " " (space) if not done
     */
    public String getIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "] " + description;
    }
}