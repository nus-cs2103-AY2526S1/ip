package hermione.tasks;

/**
 * Represents a generic task in the Hermione application.
 */
public abstract class Task {
    private final String description;
    private boolean isCompleted;

    /**
     * Constructs a Task with the description and completion status.
     *
     * @param description The description of the task.
     * @param isCompleted The completion status of the task.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isCompleted The new completion status.
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        String status = this.isCompleted ? "[X]" : "[ ]";
        return "%s %s".formatted(status, this.description);
    }

}

