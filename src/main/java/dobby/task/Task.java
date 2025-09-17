package dobby.task;

public abstract class Task {
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

    public void setDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
