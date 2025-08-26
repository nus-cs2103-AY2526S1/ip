/**
 * Abstract task with description and completion status. Subclasses should
 * define the specfic types of tasks.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description. 
     * By default, the task is marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done. 
     * Aims to simplify the toString() function.
     *
     * @return "X" if task is done, blank otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task completion marker used for saving tasks.
     * Aims to simplify the saveTaskName() function.
     *
     * @return "1" if task is done, "0" otherwise.
     */
    public String getTaskIcon() {
        return (this.isDone ? "1" : "0"); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as uncompleted.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of this task.
     * The string includes the completion status and the description.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns a formatted string representation of the task
     * suitable for saving to a file.
     *
     * @return String in the format " | <status> | <description>".
     */
    public String saveTaskName() {
        return String.format(" | %s | %s", this.getTaskIcon(), this.description);
    }
}
