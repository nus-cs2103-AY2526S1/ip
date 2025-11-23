package diablo.task;


/**
 * Represents a general task with a description. Can be marked as done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Gets the status icon of the task, "X" if done and " " if not done.
     *
     * @return String representing the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }


    /**
     * Gets the type of task in string format.
     *
     * @return An empty string.
     */
    public String getType() {
        return "";
    }


    /**
     * Gets the description of the task.
     *
     * @return String representing description of task.
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Marks the task as done.
     */
    public void complete() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", this.getStatusIcon(), this.description);
    }
}
