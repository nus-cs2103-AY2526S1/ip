package betty.task;


/**
 * Represent a Task object that has a description of task and
 * isDone to represent whether task has been completed
 */

public class Task {

    protected boolean isDone;
    protected String description;
    protected Priority priority;

    /**
     * Construct a new Task with the given description and isDone status
     *
     * @param description the details of the task
     * @param isDone      whether the task as been completed
     */
    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
        this.priority = Priority.NONE;
    }

    /**
     * Return the status icon for whether task has been completed
     * @return a String of "X" for completed and " " for uncompleted
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns the completion status of task
     * @return a boolean value of whether task has been completed
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the completion status of task isDone to be true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the completion status of task isDone to be false
     */
    public void markUndone() {
        this.isDone = false;
    }
    // getter for description
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task for display purposes
     * @return a formatted string with the task completion status and description
     */
    @Override
    public String toString() {
        String priorityStr = this.priority.equals(Priority.NONE)
                           ? ""
                           : String.format("(Priority: %s)", this.priority);
        return String.format("[%s] %s %s",
                this.getStatusIcon(),
                this.getDescription(),
                priorityStr);
    }

    /**
     * Returns the string representation of the task for storage saving purposes
     * @return a formatted string with the task completion status and description for saving into storage
     */
    public String toSaveString() {
        String completed = this.isDone ? "1" : "0";
        String priorityStr = this.priority.equals(Priority.NONE)
                           ? ""
                           : this.priority.toString();
        return String.format("%s | %s | %s",
                completed,
                this.getDescription(),
                priorityStr);
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public Priority getPriority() {
        return this.priority;
    }
}
