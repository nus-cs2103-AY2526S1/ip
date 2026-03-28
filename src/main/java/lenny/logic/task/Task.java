package lenny.logic.task;

/**
 * Represents a generic task with a description and completion status.
 * This is the parent class for all specific task types such as {@link Todo}, {@link Deadline}, and {@link Event}.
 */

public class Task {
    private String taskName;
    private Boolean isDone;
    private String taskType;
    private int priority;

    /**
     * Creates a new Task.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
    }
    public String getTaskType() {
        return "T";
    }
    /**
     * Returns whether this task is completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public Boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representation of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    public int getPriority() {
        return priority;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        this.priority = priority;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void unmark() {
        isDone = false;
    }
}
