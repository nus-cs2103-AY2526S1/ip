package borat.task;

/**
 * Base type for all tasks maintained by the application.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a task with the given description, initially not done.
     *
     * @param description Human-readable description.
     */
    Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets whether this task is done.
     *
     * @param isDone True if completed, false otherwise.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String getType();

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "][" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Returns task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }
}