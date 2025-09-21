package itachi.task;

/**
 * Represents a generic task with a description and a completion status.
 * A {@code Task} serves as the base class for more specific task types
 * like {@code Todo}, {@code Deadline}, and {@code Event}.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * Task is marked as not done initially.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + this.description;
    }
}

