package lebron.task;

import lebron.common.Constants;

/**
 * Represents a general task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *  Constructor for Task class
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description should not be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? Constants.STATUS_ICON_DONE : Constants.STATUS_ICON_NOT_DONE);
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
