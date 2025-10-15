package nyanchan.tasks;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     *
     * @param description task details
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** @return "X" if done, otherwise a blank space */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Marks this task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks this task as not done. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /** @return true if task is done */
    public boolean getDone() {
        return this.isDone;
    }

    /** @return task description */
    public String getDescription() {
        return this.description;
    }

    /** @return formatted string of the task */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
