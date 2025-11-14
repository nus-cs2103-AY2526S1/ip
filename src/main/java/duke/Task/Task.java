package duke.task;

/**
 * Represents a general task in the Duke application.
 * This is the parent class for all specific task types.
 */
public class Task {

    protected String description;
    protected String priority;
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
        this.priority = "[LOW]";
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Sets the task priority.
     *
     * @param priority priority string: low, medium, high
     */
    public void setPriority(String priority) {
        String temp = priority.toLowerCase();

        switch (temp) {
        case "low":
            this.priority = "[LOW]";
            break;
        case "medium":
            this.priority = "[MEDIUM]";
            break;
        case "high":
            this.priority = "[HIGH]";
            break;
        default:
            System.out.println("Invalid priority! Use: low, medium, or high.");
            break;
        }
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "[X]" if completed, otherwise "[ ]"
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the priority of task.
     *
     * @return The priority level of the task
     */
    public String getPriority() {
        return this.priority;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string with the priority, status icon, and description
     */
    @Override
    public String toString() {
        return getPriority() + getStatusIcon() + " " + description;
    }
}
