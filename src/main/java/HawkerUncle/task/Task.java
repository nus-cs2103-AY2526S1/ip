package HawkerUncle.task;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task { //partially taken from Level 3 A-Classes at https://nus-cs2103-ay2526s1.github.io/website/schedule/week2/project.html
    protected String description;
    protected boolean isDone;

    /**
     * Initializes the task with a description and a completion status.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task based on its completion status.
     * @return "X" if the task is completed, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task.
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Sets the completion status of the task
     * @param isDone A boolean indicating whether teh task is to be marked as completed or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Converts the task to a format suitable for saving to storage.
     * @return A string in a format suitable for saving the task to storage.
     */
    public abstract String toSaveFormat();
}
