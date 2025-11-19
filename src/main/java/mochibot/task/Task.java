package mochibot.task;

/**
 * This abstract class represents a generic {@code Task} object.
 * <p>
 *     The class is inherited by the following classes: {@link Deadline}, {@link Event} and {@link Todo}.
 * </p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "description must not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with a description and a specified completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the class.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "description must not be null";
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
