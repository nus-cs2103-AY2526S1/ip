package conversal.task;

/**
 * Represents a generic task in the Conversal chatbot.
 *
 * A task has a description, a completion status, and a task type:
 * (e.g. Todo, Deadline, Event ).
 */
public class Task {
    // Fields
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Creates a new Task with the given description and type.
     * Tasks are initially marked as incomplete.
     *
     * @param description the description of the task
     * @param taskType    the type of the task
     */
    public Task(String description, TaskType taskType) {
        assert taskType != null : "taskType must not be null";
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return the TaskType of this task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is done, false otherwise
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the task:
     * type, completion status, and description.
     *
     * @return string form of the task
     */
    @Override
    public String toString() {
        return taskType.getSymbol() + "[" + getStatusIcon() + "] " + description;
    }
}
