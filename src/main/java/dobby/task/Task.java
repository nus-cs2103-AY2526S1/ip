package dobby.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String description;
    protected boolean isDone;
    private TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false; // default
        this.type = type;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
