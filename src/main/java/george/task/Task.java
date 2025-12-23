package george.task;

import george.exceptions.GeorgeException;

/**
 * Represents an abstract task with basic properties and behaviors.
 * This serves as the base class for all specific task types in the application.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task
     * @throws GeorgeException If the description is null or empty
     */
    public Task(String description) throws GeorgeException {
        if (description == null || description.trim().isEmpty()) {
            throw new GeorgeException("You can't have an empty task. Please do something!");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return [X] if done, [ ] if not done
     */
    public String getStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the type identifier of the task.
     *
     * @return The task type as a String
     */
    abstract String getType();
    /**
     * Returns the formatted display text of the task.
     *
     * @return The formatted task information for display
     */
    public abstract String getDisplayText();
}
