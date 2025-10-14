package iris;

/** Abstract class representing a general task **/
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks the task as done or not done */
    public void markDone() { isDone = true; }
    public void markUndone() { isDone = false; }

    /** Returns status icon for the task */
    protected String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /** Returns the task description */
    public String getDescription() {
        return this.description;
    }

    public abstract String toString();
    /** Returns a string representation suitable for saving to a file **/
    public abstract String toSaveFormat();
}
