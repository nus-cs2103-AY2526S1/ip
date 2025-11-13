package task;

/**
 * Parent class of the different types of tasks handles by TaskBot.TaskBot
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    /**
     * Constructs a generic Task object
     *
     * @param type        subclass of Task object
     * @param description task name
     */
    public Task(TaskType type, String description) {
        assert description != null : "Task description must not be null";
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDesc() {
        return this.description;
    }

    public void setDescription(String newDesc) {
        this.description = newDesc;
    }
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toFileString();
}
