package airy;

/**
 * Task object to keep track of task name and status
 */
public abstract class Task {
    private final String taskName;
    private Boolean isCompleted;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Returns the completion status of the task
     *
     * @return "X" if the task is completed, a single space " " if not completed
     */
    public String getStatus() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Marks the task as completed
     */
    public void markCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as uncompleted
     */
    public void markUncompleted() {
        isCompleted = false;
    }

    /**
     * Fetches the name of the task.
     *
     * @return the task description string
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns a String format that has the completion status indicator and task name.
     *
     * @return a formatted string showing the task's status and name
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), getTaskName());
    }


    /**
     * Gets extra details, esp for Tasks like Deadline and Event for Storage class
     *
     * @return a string containing task-specific details in storage format
     */
    public abstract String getExtraDetailsForStorage();
}
