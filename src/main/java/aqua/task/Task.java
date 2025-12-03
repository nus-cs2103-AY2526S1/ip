package aqua.task;

import aqua.exception.InvalidArgumentException;

/**
 * Abstract class representing a Task with a description and completion status.
 */
public abstract class Task {
    /**
     * Priority levels for tasks.
     */
    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
    }

    /**
     * Description of the task
     */
    protected String description;

    /**
     * Completion status of the task
     */
    protected boolean isDone;

    /**
     * Priority of the task
     */
    protected Priority priority;

    /**
     * Create a Task
     *
     * @param description description of the task
     * @throws InvalidArgumentException If the description is empty
     */
    public Task(String description) throws InvalidArgumentException {
        if (description.isBlank()) {
            throw new InvalidArgumentException("Description cannot be empty");
        }

        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority to set
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Marks the task as completed
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isDone ? "[X]" : "[ ]").append(" ")
                .append(this.description).append(" ")
                .append(this.priority == null ? "" : String.format("(Priority: %s)", this.priority));
        return sb.toString();
    }

    /**
     * Returns the string representation of the task to be stored in the storage file.
     *
     * @return String representation of the task for storage
     */
    public abstract String toStorageString();
}
